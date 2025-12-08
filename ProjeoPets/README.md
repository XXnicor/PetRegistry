# PetRegistry

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green)
![Maven](https://img.shields.io/badge/Build-Maven-red)

API REST para gerenciamento de pets em ONGs de proteção animal.

## Sobre

Começou como um desafio técnico do canal DevMagro (focado em fundamentos Java) e evoluiu para uma solução voltada para ONGs que precisam gerenciar animais resgatados.

## Funcionalidades

- CRUD completo de Pets
- Controle de status (Disponível, Adotado, Em Tratamento, etc.)
- Gerenciamento de Lares Temporários
- Cadastro de Adotantes
- Histórico de eventos por pet
- Interface web básica para visualização

## Tecnologias

**Backend:**
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA + Hibernate
- Maven

**Banco de Dados:**
- H2 (desenvolvimento)
- PostgreSQL (produção)

**Frontend:**
- HTML/CSS/JavaScript
- Bootstrap 5

## Como Executar

```bash
# Clone o repositório
git clone https://github.com/XXnicor/PetRegistry.git
cd PetRegistry

# Compile e execute
mvn clean compile
mvn spring-boot:run -DskipTests
```

Acesse:
- Frontend: http://localhost:9090
- API: http://localhost:9090/api/pets
- H2 Console: http://localhost:9090/h2-console (user: sa, password: password)

## Principais Endpoints

```
GET    /api/pets                    Lista todos os pets
GET    /api/pets/{id}               Busca por ID
GET    /api/pets/status/{status}    Filtra por status
POST   /api/pets                    Cadastra pet
PUT    /api/pets/{id}               Atualiza pet
DELETE /api/pets/{id}               Remove pet
```

## Exemplo de Cadastro

```json
POST /api/pets
{
  "nome": "Rex",
  "petType": "CACHORRO",
  "sexType": "MACHO",
  "portePet": "MEDIO",
  "statusPet": "DISPONIVEL_ADOCAO",
  "idade": 3,
  "castrado": true,
  "vacinado": true,
  "entradaPet": "2025-12-08"
}
```

## Estrutura do Projeto

```
src/main/java/br/com/PetRegistry/
├── controller/     # Controllers REST
├── service/        # Lógica de negócio
├── repository/     # Acesso a dados
├── model/          # Entidades
└── DTORequests/    # DTOs
```

## Testes

```bash
mvn test
```

Implementados:
- Testes de serviço (cadastro, atualização)
- Testes de conversão (DTOs)
- Testes de validação

## Configuração para Produção

Edite `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/petregistry
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

Execute:
```bash
java -jar target/PetRegistry-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

## Aprendizados

- Arquitetura em camadas
- Relacionamentos JPA
- Validações com Bean Validation
- Integração frontend-backend
- CORS para APIs REST

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

