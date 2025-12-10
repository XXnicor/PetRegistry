package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.AdotanteRequestDTO;
import br.com.PetRegistry.DTORequests.AdotanteUpdateDTO;
import br.com.PetRegistry.model.Adotante;
import br.com.PetRegistry.Util.RegrasDeNegocioExceptions;
import br.com.PetRegistry.repository.AdotanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdotanteService {
    private AdotanteRepository adotanteRepository;

    public AdotanteService(AdotanteRepository adotanteRepository) {
        this.adotanteRepository = adotanteRepository;
    }

    public Adotante cadastrarAdotante(AdotanteRequestDTO dto) {
        if (dto == null) {
            throw new RegrasDeNegocioExceptions("Dados do adotante são obrigatórios");
        }
        Adotante novoAdotante = new Adotante(
                0,
                dto.getNomeResponsavel(),
                dto.getCpf(),
                dto.getContato(),
                dto.getEnderecoCompleto(),
                dto.getDataAprovacao()
        );

        return adotanteRepository.save(novoAdotante);
    }

    public Adotante buscarAdotantePorId(Long id) {
        return adotanteRepository.findById(id)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Adotante não encontrado com ID: " + id));
    }

    public List<Adotante> listarTodosAdotantes() {
        return adotanteRepository.findAll();
    }

    public Adotante atualizarAdotante(long id, AdotanteUpdateDTO dto) {
        Adotante adotanteExistente = buscarAdotantePorId(id);

        adotanteExistente.setNomeCompleto(dto.getNomeResponsavel());
        adotanteExistente.setContato(dto.getContato());
        adotanteExistente.setEndereco(dto.getEnderecoCompleto());

        return adotanteRepository.update(adotanteExistente);
    }

    public void removerAdotante(long id) {
        buscarAdotantePorId(id);
        adotanteRepository.deleteById(id);
    }
}
