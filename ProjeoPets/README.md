# PetRegistry API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green)
![JPA/Hibernate](https://img.shields.io/badge/JPA-Hibernate-orange)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Maven](https://img.shields.io/badge/Build-Maven-red)

API RESTful para gerenciamento de animais em uma ONG de proteção, permitindo o controle de cadastros, status, lares temporários e histórico de eventos.

## 🛠️ Tecnologias e Práticas

**Stack:**
- Java 17, Spring Boot 3
- Spring Data JPA, Hibernate
- PostgreSQL (produção), H2 (desenvolvimento)
- Maven, JUnit 5, Mockito

**Arquitetura:**
- Separação em camadas (Controller/Service/Repository/Model)
- DTOs para requisições e respostas
- Relacionamentos JPA com objetos
- Transações gerenciadas com @Transactional
- Testes unitários com mocks

## ▶️ Como Executar

**Pré-requisitos:**
- Java 17+
- Maven 3.8+

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ProjeoPets.git
cd ProjeoPets

# Compile
mvn clean package

# Execute
java -jar target/PetRegistry-1.0-SNAPSHOT.jar
```

A API estará disponível em `http://localhost:8080`.

## 🔌 Principais Endpoints

### Pets
- `GET /pets` - Lista todos os pets (paginação)
- `GET /pets/{id}` - Busca pet por ID
- `POST /pets` - Cadastra novo pet
- `PUT /pets/{id}` - Atualiza pet
- `DELETE /pets/{id}` - Remove pet

### Lares Temporários
- `GET /lares-temporarios` - Lista lares
- `POST /lares-temporarios` - Cadastra lar

### Adotantes
- `GET /adotantes` - Lista adotantes
- `POST /adotantes` - Cadastra adotante

## 📝 Exemplo de Requisição

```json
POST /pets
{
  "nome": "Rex",
  "petType": "CACHORRO",
  "idade": 3,
  "statusPet": "DISPONIVEL_ADOCAO",
  "descricao": "Cachorro dócil",
  "castrado": true,
  "vacinado": true
}
```

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/PetRegistry/
│   │   ├── controller/     # Controllers REST
│   │   ├── service/        # Lógica de negócio
│   │   ├── repository/     # Acesso a dados
│   │   ├── model/          # Entidades JPA
│   │   └── DTORequests/    # DTOs
│   └── resources/
│       ├── application.properties
│       └── static/         # Frontend simples
└── test/                   # Testes unitários
```

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar testes de um serviço específico
mvn test -Dtest=PetServiceTest
```

## 📚 Funcionalidades

- ✅ CRUD completo de Pets
- ✅ CRUD de Lares Temporários
- ✅ CRUD de Adotantes
- ✅ Controle de status (Disponível, Adotado, Em Tratamento)
- ✅ Histórico de eventos por pet
- ✅ Relacionamento entre Pet e Lar Temporário
- ✅ Validações de dados com Bean Validation

## 🔧 Configuração do Banco

### Desenvolvimento (H2)
Por padrão, usa banco H2 em memória. Sem configuração necessária.

### Produção (PostgreSQL)
Configure em `application-prod.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/petregistry
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

Execute com:
```bash
java -jar target/PetRegistry-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

---

*Desenvolvido por Nicolas*

