[README.md](https://github.com/user-attachments/files/24110499/README.md)
# 🐾 PetRegistry - Sistema de Gerenciamento de Pets

Sistema completo de gerenciamento para ONGs de adoção de animais, desenvolvido com Spring Boot e interface web interativa.

## 📋 Sobre o Projeto

O PetRegistry oferece uma API REST e uma interface web para acompanhar todo o ciclo de cadastro, adoção e acompanhamento de animais resgatados. A ideia é centralizar os dados de pets, adotantes e lares temporários em uma ferramenta simples de manter e expandir.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
  - Spring Web
  - Spring JDBC Template
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

**Cadastro de Pet** – fluxo completo de registro pelo formulário web.

<img src="Docs/gifs/browser/Cadastro%20do%20pet.gif" width="700" alt="Cadastro de pet no navegador" />

**Edição de Pet** – atualização dos dados direto pela interface.

<img src="Docs/gifs/browser/Edi%C3%A7%C3%A3o%20do%20pet.gif" width="700" alt="Edição de pet no navegador" />

**Exclusão de Pet** – remoção segura com feedback visual.

<img src="Docs/gifs/browser/Exclus%C3%A3o%20do%20pet.gif" width="700" alt="Exclusão de pet no navegador" />

**Fluxo Completo de Adoção** – sequência do cadastro até a conclusão da adoção.

<img src="Docs/gifs/browser/fluxo%20de%20ado%C3%A7ao.gif" width="700" alt="Fluxo de adoção no navegador" />

---

### API REST (Postman)

**Inserir Pet** – request POST enviando o payload completo.

<img src="Docs/gifs/postman/Inseriri%20pet.gif" width="700" alt="Inserção de pet via Postman" />

**Listar Todos os Pets** – visão geral dos registros salvos.

<img src="Docs/gifs/postman/Listar%20pets.gif" width="700" alt="Listagem de pets via Postman" />

**Listar Pet por ID** – busca pontual validando os filtros.

<img src="Docs/gifs/postman/Listar%20pet%20por%20id.gif" width="700" alt="Listagem de pet por ID via Postman" />

**Atualizar Pet** – envio do PUT com apenas os campos modificados.

<img src="Docs/gifs/postman/Atualizar.gif" width="700" alt="Atualização de pet via Postman" />

**Deletar Pet** – exclusão confirmada no endpoint DELETE.

<img src="Docs/gifs/postman/Delete.gif" width="700" alt="Remoção de pet via Postman" />

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
│   │   │   ├── model/        # Modelos utilizados pelo JDBC
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
