// ====================================================================
// SCRIPT DE INTEGRAÇÃO FRONTEND ↔ BACKEND - PetRegistry
// ====================================================================
// Este arquivo conecta o Index.html aos métodos do PetController
// Para usar em produção: adicionar endpoints REST (Spring Boot)
// ====================================================================

// Configuração da API (ajuste conforme seu servidor)
const API_BASE_URL = 'http://localhost:8081/api';

// ================================================================
// Autenticação básica para operações de escrita (Sprint 1 - Segurança)
// - Se variáveis ADMIN_USER/ADMIN_PASS estiverem definidas no servidor,
//   as rotas POST/PUT/DELETE exigirão Authorization: Basic
// - Em DEV (sem essas variáveis), não é necessário autenticar
// ================================================================
let ADMIN_AUTH = null;

function loadSavedAuth() {
    try {
        const user = localStorage.getItem('adminUser');
        const pass = localStorage.getItem('adminPass');
        if (user && pass) {
            ADMIN_AUTH = { user, pass };
        }
    } catch (_) { /* ignore */ }
}

function saveAuth(user, pass) {
    try {
        localStorage.setItem('adminUser', user);
        localStorage.setItem('adminPass', pass);
    } catch (_) { /* ignore */ }
}

function authHeader() {
    if (!ADMIN_AUTH) return {};
    const token = btoa(`${ADMIN_AUTH.user}:${ADMIN_AUTH.pass}`);
    return { 'Authorization': `Basic ${token}` };
}

async function ensureAuthInteractive() {
    const user = prompt('Usuário admin:');
    if (user === null) return false;
    const pass = prompt('Senha:');
    if (pass === null) return false;
    ADMIN_AUTH = { user, pass };
    saveAuth(user, pass);
    return true;
}

// ====================================================================
// 1. LISTAR TODOS OS PETS (ao carregar a página)
// ====================================================================
async function carregarPets() {
    try {
        const response = await fetch(`${API_BASE_URL}/pets`);
        if (!response.ok) throw new Error('Erro ao carregar pets');

        const pets = await response.json();
        renderizarPets(pets);
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao carregar pets do servidor');
    }
}

// ====================================================================
// 2. RENDERIZAR PETS NO GRID
// ====================================================================
function renderizarPets(pets) {
    const petGrid = document.getElementById('petGrid');
    petGrid.innerHTML = ''; // Limpar grid

    pets.forEach(pet => {
        const card = criarCardPet(pet);
        petGrid.appendChild(card);
    });
}

// ====================================================================
// 3. CRIAR CARD HTML PARA UM PET
// ====================================================================
function criarCardPet(pet) {
    const col = document.createElement('div');
    col.className = 'col-md-4';

    // Mapear status para badge colorido
    const statusBadge = {
        'DISPONIVEL_ADOCAO': 'bg-success',
        'EM_TRATAMENTO': 'bg-danger',
        'EM_LAR_TEMPORARIO': 'bg-warning text-dark',
        'ADOTADO': 'bg-secondary',
        'RESERVADO': 'bg-info'
    };

    // Mapear tipo para exibição
    const tipoDisplay = {
        'CACHORRO': 'Cachorro',
        'GATO': 'Gato',
        'OUTRO': 'Outro'
    };

    // Mapear sexo para exibição
    const sexoDisplay = {
        'MACHO': 'Macho',
        'FEMEA': 'Fêmea'
    };

    // Mapear porte para exibição
    const porteDisplay = {
        'PEQUENO': 'Pequeno',
        'MEDIO': 'Médio',
        'GRANDE': 'Grande'
    };

    // Mapear status para texto
    const statusDisplay = {
        'DISPONIVEL_ADOCAO': 'Disponível para Adoção',
        'EM_TRATAMENTO': 'Em Tratamento',
        'EM_LAR_TEMPORARIO': 'Em Lar Temporário',
        'ADOTADO': 'Adotado',
        'RESERVADO': 'Reservado'
    };

    col.innerHTML = `
        <div class="card shadow-sm h-100">
            <img src="${pet.fotoUrl || 'https://via.placeholder.com/250'}" 
                 class="card-img-top" 
                 alt="${pet.nomeCompleto}" 
                 style="height: 250px; object-fit: cover;">
            <div class="card-body d-flex flex-column">
                <h5 class="card-title">${pet.nomeCompleto}</h5>
                <p class="card-text mb-2 text-muted">
                    ${tipoDisplay[pet.tipo]} | 
                    ${sexoDisplay[pet.sexo]} | 
                    ${porteDisplay[pet.porte]}
                </p>
                <span class="badge ${statusBadge[pet.status]} fs-6 mb-3 align-self-start">
                    ${statusDisplay[pet.status]}
                </span>
                <p class="card-text">${pet.observacoes || 'Sem observações'}</p>
                <div class="mt-auto d-flex justify-content-end gap-2">
                    <button class="btn btn-outline-secondary btn-sm" onclick="editarPet(${pet.id})">
                        <i class="bi bi-pencil-fill"></i> Editar
                    </button>
                    <button class="btn btn-danger btn-sm" onclick="excluirPet(${pet.id})">
                        <i class="bi bi-trash-fill"></i> Excluir
                    </button>
                </div>
            </div>
        </div>
    `;

    return col;
}

// ====================================================================
// 4. CADASTRAR / ATUALIZAR PET (botão "Salvar Pet")
// ====================================================================
let CURRENT_EDIT_ID = null; // nulo = modo cadastro; número = modo edição

document.getElementById('btnSalvarPet').addEventListener('click', async () => {
    if (CURRENT_EDIT_ID) {
        await atualizarPetExistente(CURRENT_EDIT_ID);
    } else {
        await cadastrarNovoPet();
    }
});

async function cadastrarNovoPet() {
    // Coletar dados do formulário (PetRequestDTO)
    const petData = {
        nome: document.getElementById('nome').value,
        fotoUrl: document.getElementById('fotoUrl').value || null,
        especie: null,
        raca: null,
        idade: parseInt(document.getElementById('idade').value) || 0,
        descricao: document.getElementById('observacoes').value,
        historicoSaude: null,
        castrado: document.getElementById('castrado').checked,
        vacinado: document.getElementById('vacinado').checked,
        petType: document.getElementById('tipo').value,
        sexType: document.getElementById('sexo').value,
        portePet: document.getElementById('porte').value,
        statusPet: document.getElementById('status').value,
        entradaPet: document.getElementById('dataEntrada').value
    };

    try {
        let response = await fetch(`${API_BASE_URL}/pets`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', ...authHeader() },
            body: JSON.stringify(petData)
        });
        if (response.status === 401) {
            const got = await ensureAuthInteractive();
            if (!got) return;
            response = await fetch(`${API_BASE_URL}/pets`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', ...authHeader() },
                body: JSON.stringify(petData)
            });
        }
        if (!response.ok) {
            const err = await safeError(response);
            throw new Error(err);
        }
        alert('Pet cadastrado com sucesso!');
        fecharModalResetar();
        carregarPets();
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao cadastrar pet: ' + (error.message || 'Verifique os dados.'));
    }
}

async function atualizarPetExistente(id) {
    // Montar corpo com PetUpdateDTO
    const updateData = {
        nome: document.getElementById('nome').value || null,
        idade: parseInt(document.getElementById('idade').value) || 0,
        descricao: document.getElementById('observacoes').value || null,
        historicoSaude: null, // não coletado na UI
        portePet: document.getElementById('porte').value || null
    };

    try {
        let response = await fetch(`${API_BASE_URL}/pets/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json', ...authHeader() },
            body: JSON.stringify(updateData)
        });
        if (response.status === 401) {
            const got = await ensureAuthInteractive();
            if (!got) return;
            response = await fetch(`${API_BASE_URL}/pets/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json', ...authHeader() },
                body: JSON.stringify(updateData)
            });
        }
        if (!response.ok) {
            const err = await safeError(response);
            throw new Error(err);
        }
        alert('Pet atualizado com sucesso!');
        fecharModalResetar();
        carregarPets();
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao atualizar pet: ' + (error.message || ''));
    }
}

// ====================================================================
// 5. FILTRAR PETS POR STATUS
// ====================================================================
document.getElementById('filtroStatus').addEventListener('change', async (e) => {
    const status = e.target.value;

    if (status === 'todos') {
        carregarPets();
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/pets/status/${status}`);
        if (!response.ok) throw new Error('Erro ao filtrar pets');

        const pets = await response.json();
        renderizarPets(pets);
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao filtrar pets');
    }
});

// ====================================================================
// 6. EXCLUIR PET
// ====================================================================
async function excluirPet(petId) {
    if (!confirm('Tem certeza que deseja excluir este pet?')) {
        return;
    }

    try {
        let response = await fetch(`${API_BASE_URL}/pets/${petId}`, {
            method: 'DELETE',
            headers: {
                ...authHeader()
            }
        });

        if (response.status === 401) {
            const got = await ensureAuthInteractive();
            if (!got) return;
            response = await fetch(`${API_BASE_URL}/pets/${petId}`, {
                method: 'DELETE',
                headers: {
                    ...authHeader()
                }
            });
        }

        if (!response.ok) {
            const err = await safeError(response);
            throw new Error(err);
        }

        alert('Pet excluído com sucesso!');
        carregarPets();
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao excluir pet: ' + (error.message || '')); 
    }
}

// ====================================================================
// 7. EDITAR PET (TODO: implementar modal de edição)
// ====================================================================
async function editarPet(petId) {
    try {
        const response = await fetch(`${API_BASE_URL}/pets/${petId}`);
        if (!response.ok) throw new Error('Erro ao buscar pet');

        const pet = await response.json();

        // Preencher modal de edição com dados do pet
        preencherFormularioComPet(pet);
        CURRENT_EDIT_ID = pet.id;

        // Ajustar título e botão
        const modalEl = document.getElementById('addPetModal');
        const title = modalEl.querySelector('#modalLabel');
        if (title) title.textContent = `Editar Pet: ${pet.nomeCompleto}`;
        const btnSalvar = document.getElementById('btnSalvarPet');
        if (btnSalvar) btnSalvar.textContent = 'Atualizar Pet';

        // Abrir modal
        const bsModal = new bootstrap.Modal(modalEl);
        bsModal.show();

    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao buscar dados do pet');
    }
}

function preencherFormularioComPet(pet) {
    // Mapa DTO -> campos do formulário
    document.getElementById('nome').value = pet.nomeCompleto || '';
    document.getElementById('fotoUrl').value = pet.fotoUrl || '';
    document.getElementById('tipo').value = pet.tipo || 'CACHORRO';
    document.getElementById('sexo').value = pet.sexo || 'MACHO';
    document.getElementById('porte').value = pet.porte || 'MEDIO';
    document.getElementById('status').value = pet.status || 'DISPONIVEL_ADOCAO';
    document.getElementById('idade').value = pet.idadeAproximada || 0;
    // dataEntrada vem em ISO yyyy-MM-dd
    if (pet.dataEntrada) {
        document.getElementById('dataEntrada').value = pet.dataEntrada;
    }
    document.getElementById('observacoes').value = pet.observacoes || '';
    // castrado/vacinado não estão no DTO de resposta atualmente — mantemos como está
}

function fecharModalResetar() {
    const modalEl = document.getElementById('addPetModal');
    const modal = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
    modal.hide();
    document.getElementById('petForm').reset();
    // Resetar UI para modo cadastro
    CURRENT_EDIT_ID = null;
    const title = modalEl.querySelector('#modalLabel');
    if (title) title.textContent = 'Cadastrar Novo Pet';
    const btnSalvar = document.getElementById('btnSalvarPet');
    if (btnSalvar) btnSalvar.textContent = 'Salvar Pet';
}

// ====================================================================
// 8. CARREGAR PETS AO INICIALIZAR A PÁGINA
// ====================================================================
document.addEventListener('DOMContentLoaded', () => {
    console.log('🐾 PetRegistry carregado!');
    loadSavedAuth();
    carregarPets(); // ✅ Agora está ativo!
    // Botão de upload de foto (Sprint 2 - Upload)
    const btnUpload = document.getElementById('btnUploadFoto');
    if (btnUpload) {
        btnUpload.addEventListener('click', uploadFoto);
    }
    // Quando o modal fechar, resetar o estado (voltar para modo cadastro)
    const modalEl = document.getElementById('addPetModal');
    if (modalEl) {
        modalEl.addEventListener('hidden.bs.modal', () => fecharModalResetar());
    }
});

// ====================================================================
// ENDPOINTS QUE VOCÊ PRECISA CRIAR NO BACKEND:
// ====================================================================
// GET    /api/pets              → listarTodosOsPets()
// GET    /api/pets/{id}         → buscarPetPorId(id)
// GET    /api/pets/status/{s}   → listarPetsPorStatus(status)
// POST   /api/pets              → cadastrarPet(dto)
// PUT    /api/pets/{id}         → atualizarPet(id, dto)
// DELETE /api/pets/{id}         → removerPet(id)
// ====================================================================


// Utilitário: tenta extrair mensagem de erro do backend
async function safeError(response) {
    try {
        const data = await response.json();
        if (data) {
            if (data.message) return data.message;
            if (data.error) return data.error;
        }
        return `HTTP ${response.status}`;
    } catch (_) {
        return `HTTP ${response.status}`;
    }
}

// ====================================================================
// Upload de imagem (Cloudinary via backend) - Sprint 2
// - Envia arquivo selecionado (#fileFoto) para POST /api/upload
// - Preenche automaticamente o campo #fotoUrl com a URL retornada
// ====================================================================
async function uploadFoto() {
    const fileInput = document.getElementById('fileFoto');
    const file = fileInput && fileInput.files ? fileInput.files[0] : null;
    const fotoUrlInput = document.getElementById('fotoUrl');

    try {
        let response;
        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            response = await fetch(`${API_BASE_URL}/upload`, {
                method: 'POST',
                headers: {
                    // Não definir Content-Type manualmente com FormData
                    ...authHeader()
                },
                body: formData
            });

            if (response.status === 401) {
                const got = await ensureAuthInteractive();
                if (!got) return;
                response = await fetch(`${API_BASE_URL}/upload`, {
                    method: 'POST',
                    headers: { ...authHeader() },
                    body: formData
                });
            }
        } else {
            // Sem arquivo: permitir informar uma URL e encaminhar ao backend (opcional)
            const provided = (fotoUrlInput && fotoUrlInput.value) ? fotoUrlInput.value.trim() : '';
            if (!provided) {
                alert('Selecione um arquivo de imagem ou informe uma URL no campo acima.');
                return;
            }
            response = await fetch(`${API_BASE_URL}/upload`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', ...authHeader() },
                body: JSON.stringify({ imageUrl: provided })
            });
            if (response.status === 401) {
                const got = await ensureAuthInteractive();
                if (!got) return;
                response = await fetch(`${API_BASE_URL}/upload`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json', ...authHeader() },
                    body: JSON.stringify({ imageUrl: provided })
                });
            }
        }

        if (!response.ok) {
            const err = await safeError(response);
            throw new Error(err);
        }

        const data = await response.json();
        if (data && data.url) {
            if (fotoUrlInput) fotoUrlInput.value = data.url;
            alert('Upload concluído! URL preenchida.');
        } else {
            throw new Error('Resposta inesperada do servidor');
        }
    } catch (error) {
        console.error('Erro no upload:', error);
        alert('Erro no upload: ' + (error.message || ''));
    }
}
