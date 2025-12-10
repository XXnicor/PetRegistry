# PetRegistry

API REST para gerenciamento de pets em ONGs de proteção animal.

## Sobre

Projeto iniciado como desafio técnico do canal DevMagro e expandido para demonstrar conhecimentos em Spring Boot e desenvolvimento de APIs REST.

## Funcionalidades

- CRUD de Pets
- Controle de status dos animais
- Gerenciamento de Lares Temporários
- Cadastro de Adotantes
- Histórico de eventos
- Interface web simples

## Tecnologias

- Java 17
- Spring Boot 3.2.5
- Spring JDBC
- PostgreSQL
- Maven

## Como Executar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL

### Setup

1. **Criar o banco de dados:**

```bash
psql -U postgres -c "CREATE DATABASE petdb;"
```

2. **Configurar credenciais:**

Edite `src/main/resources/application.properties` com suas credenciais do PostgreSQL.

3. **Executar:**

```bash
mvn spring-boot:run
```

O schema SQL será criado automaticamente na primeira execução.

Acesse: http://localhost:9090

## Endpoints

```
GET    /api/pets
GET    /api/pets/{id}
GET    /api/pets/status/{status}
POST   /api/pets
PUT    /api/pets/{id}
DELETE /api/pets/{id}
```

## Estrutura

```
src/main/java/br/com/PetRegistry/
├── controller/
├── service/
├── repository/
├── model/
└── DTORequests/
```

## Roadmap

- [ ] Autenticação JWT
- [ ] Upload de imagens
- [ ] Paginação avançada
- [ ] Documentação Swagger

## Contato

Nicolas Eduardo  
Email: nicoedu123@gmail.com  
GitHub: [@XXnicor](https://github.com/XXnicor)

---

Projeto desenvolvido a partir do desafio do DevMagro, aplicando conceitos de Spring Boot e boas práticas de desenvolvimento.

