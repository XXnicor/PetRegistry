# 🐾 PetRegistry - Sistema de Gerenciamento de Pets

Sistema completo de gerenciamento para ONGs de adoção de animais, desenvolvido com Spring Boot e interface web interativa.

## 📋 Sobre o Projeto

O PetRegistry é uma API REST robusta para gerenciamento de pets, adotantes e lares temporários, facilitando o processo de adoção e acompanhamento de animais resgatados por ONGs e instituições de proteção animal.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL** - Banco de dados relacional
- **Maven** - Gerenciamento de dependências
- **H2 Database** - Testes

## ⚙️ Funcionalidades

- ✅ Cadastro, edição e exclusão de pets
- ✅ Gerenciamento de adotantes
- ✅ Controle de lares temporários
- ✅ Processo completo de adoção
- ✅ Validação de dados com Bean Validation
- ✅ Interface web responsiva
- ✅ API REST documentada

## 📦 Pré-requisitos

- Java 17 ou superior
- PostgreSQL 12 ou superior
- Maven 3.6 ou superior

## 🔧 Configuração e Instalação

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd ProjeoPets
```

2. **Configure o banco de dados**

Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE ProjetoPets;
```

3. **Configure as credenciais**

Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ProjetoPets
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

4. **Execute os scripts de criação**
```bash
# O schema.sql será executado automaticamente na inicialização
# Ou execute manualmente:
psql -U postgres -d ProjetoPets -f create_database.sql
```

5. **Compile e execute o projeto**
```bash
mvn clean install
mvn spring-boot:run
```

6. **Acesse a aplicação**
- API: `http://localhost:9090`
- Interface Web: `http://localhost:9090/Index.html`

## 📊 Modelo de Dados

![Diagrama de Classes](Diagraman.png)

## 🎥 Demonstrações

### Interface Web (Browser)

#### Cadastro de Pet
https://github.com/user-attachments/assets/cadastro-pet-demo.mp4

<video src="Docs/gifs/browser/Cadastro do pet.mp4" width="600" controls></video>

#### Edição de Pet
https://github.com/user-attachments/assets/edicao-pet-demo.mp4

<video src="Docs/gifs/browser/Edição do pet.mp4" width="600" controls></video>

#### Exclusão de Pet
https://github.com/user-attachments/assets/exclusao-pet-demo.mp4

<video src="Docs/gifs/browser/Exclusão do pet.mp4" width="600" controls></video>

#### Fluxo Completo de Adoção
https://github.com/user-attachments/assets/fluxo-adocao-demo.mp4

<video src="Docs/gifs/browser/fluxo de adoçao.mp4" width="600" controls></video>

---

### API REST (Postman)

#### Inserir Pet
https://github.com/user-attachments/assets/inserir-pet-api-demo.mp4

<video src="Docs/gifs/postman/Inseriri pet.mp4" width="600" controls></video>

#### Listar Todos os Pets
https://github.com/user-attachments/assets/listar-pets-api-demo.mp4

<video src="Docs/gifs/postman/Listar pets.mp4" width="600" controls></video>

#### Listar Pet por ID
https://github.com/user-attachments/assets/listar-pet-id-api-demo.mp4

<video src="Docs/gifs/postman/Listar pet por id.mp4" width="600" controls></video>

#### Atualizar Pet
https://github.com/user-attachments/assets/atualizar-pet-api-demo.mp4

<video src="Docs/gifs/postman/Atualizar.mp4" width="600" controls></video>

#### Deletar Pet
https://github.com/user-attachments/assets/deletar-pet-api-demo.mp4

<video src="Docs/gifs/postman/Delete.mp4" width="600" controls></video>

## 🛠️ Endpoints da API

### Pets

- `GET /pets` - Lista todos os pets
- `GET /pets/{id}` - Busca pet por ID
- `POST /pets` - Cadastra novo pet
- `PUT /pets/{id}` - Atualiza pet
- `DELETE /pets/{id}` - Remove pet

### Adotantes

- `GET /adotantes` - Lista todos os adotantes
- `GET /adotantes/{id}` - Busca adotante por ID
- `POST /adotantes` - Cadastra novo adotante
- `PUT /adotantes/{id}` - Atualiza adotante
- `DELETE /adotantes/{id}` - Remove adotante

### Lares Temporários

- `GET /lares-temporarios` - Lista todos os lares temporários
- `GET /lares-temporarios/{id}` - Busca lar temporário por ID
- `POST /lares-temporarios` - Cadastra novo lar temporário
- `PUT /lares-temporarios/{id}` - Atualiza lar temporário
- `DELETE /lares-temporarios/{id}` - Remove lar temporário

## 📝 Exemplos de Requisições

### Cadastrar um Pet

```http
POST /pets
Content-Type: application/json

{
  "nome": "Rex",
  "especie": "CACHORRO",
  "raca": "Labrador",
  "idade": 3,
  "peso": 25.5,
  "descricao": "Cachorro dócil e brincalhão",
  "status": "DISPONIVEL"
}
```

### Listar Pets

```http
GET /pets
```

## 🧪 Testes

Execute os testes unitários:
```bash
mvn test
```

## 📁 Estrutura do Projeto

```
ProjeoPets/
├── src/
│   ├── main/
│   │   ├── java/br/com/PetRegistry/
│   │   │   ├── api/          # Adaptadores e serializadores
│   │   │   ├── config/       # Configurações (CORS, etc)
│   │   │   ├── controller/   # Controllers REST
│   │   │   ├── DTORequests/  # DTOs de requisição
│   │   │   ├── model/        # Entidades JPA
│   │   │   ├── repository/   # Repositórios
│   │   │   ├── service/      # Lógica de negócio
│   │   │   └── Util/         # Utilitários
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       └── static/       # Frontend
│   └── test/                 # Testes unitários
├── Docs/                     # Documentação e demos
├── pom.xml
└── README.md
```

## 👥 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## 📄 Licença

Este projeto está sob a licença MIT.

## 📧 Contato

Para dúvidas ou sugestões, entre em contato através do GitHub.

---

⭐ Desenvolvido com dedicação para facilitar o trabalho de ONGs e instituições de proteção animal.

