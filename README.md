# 🐾 Pet Registry

API REST para gerenciamento de pets em ONGs de proteção animal, desenvolvida com Spring Boot e boas práticas de desenvolvimento.

## 📋 Sobre o Projeto

Aplicação iniciada como desafio técnico do canal **DevMagro** e expandida para demonstrar conhecimentos sólidos em: 
- Desenvolvimento de APIs REST
- Framework Spring Boot
- Arquitetura em camadas
- Integração com banco de dados relacional

## ✨ Funcionalidades

- ✅ **CRUD completo de Pets** - Criar, ler, atualizar e excluir registros de animais
- 📊 **Controle de status dos animais** - Disponível, adotado, em tratamento, etc.
- 🏠 **Gerenciamento de Lares Temporários** - Cadastro e controle de lares temporários
- 👥 **Cadastro de Adotantes** - Registro de pessoas interessadas em adoção
- 📝 **Histórico de eventos** - Rastreamento de eventos importantes na vida do animal
- 🖥️ **Interface web simples** - Interface básica para interação com a API

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.5** - Framework principal
- **Spring JDBC** - Acesso a dados
- **PostgreSQL** - Banco de dados relacional
- **Maven** - Gerenciamento de dependências

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL

### Configuração

1. **Criar o banco de dados:**
   ```bash
   psql -U postgres -c "CREATE DATABASE petdb;"
   ```

2. **Configurar credenciais:**
   
   Edite o arquivo `src/main/resources/application.properties` com suas credenciais do PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/petdb
   spring.datasource. username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

O esquema SQL será criado automaticamente na primeira execução.

### Acesso

A aplicação estará disponível em:  **http://localhost:9090**

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/pets` | Lista todos os pets |
| `GET` | `/api/pets/{id}` | Busca pet por ID |
| `GET` | `/api/pets/status/{status}` | Busca pets por status |
| `POST` | `/api/pets` | Cadastra novo pet |
| `PUT` | `/api/pets/{id}` | Atualiza dados do pet |
| `DELETE` | `/api/pets/{id}` | Remove pet do sistema |

## 📁 Estrutura do Projeto

```
src/main/java/br/com/PetRegistry/
├── controller/      # Camada de controle (endpoints)
├── service/         # Camada de lógica de negócio
├── repository/      # Camada de acesso aos dados
├── model/           # Entidades e modelos de domínio
└── DTORequests/     # Objetos de transferência de dados
```

## 🎯 Próximos Passos

- [ ] Implementar autenticação JWT
- [ ] Adicionar upload de imagens dos pets
- [ ] Implementar paginação nos endpoints
- [ ] Criar documentação interativa com Swagger/OpenAPI
- [ ] Adicionar testes unitários e de integração

## 📬 Contato

**Nicolas Eduardo**

- 📧 Email: nicoedu123@gmail. com
- 💻 GitHub: [@XXnicor](https://github.com/XXnicor)

---

*Projeto desenvolvido a partir do desafio do DevMagro, aplicando conceitos de Spring Boot e boas práticas de desenvolvimento.*
