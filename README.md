Registro de animais de estimação
API REST para gerenciamento de pets em ONGs de proteção animal.

Sobre
Projeto iniciado como desafio técnico do canal DevMagro e expandido para demonstração de conhecimentos em Spring Boot e desenvolvimento de APIs REST.

Funcionalidades
CRUD de Pets
Controle de status dos animais
Gerenciamento de Lares Temporários
Cadastro de Adotantes
Histórico de eventos
Interface web simples
Órgãos transversais
Java 17
Spring Boot 3.2.5
Spring JDBC
PostgreSQL
Maven
Como Executar
Pré-requisitos
Java 17+
Maven
PostgreSQL
Configurar
Criar o banco de dados:
psql -U postgres -c "CREATE DATABASE petdb;"
Crenças de unidade:
Edite src/main/resources/application.propertiescom suas credenciais do PostgreSQL.

Executor:
mvn spring-boot:run
O esquema SQL será criado automaticamente na primeira execução.

: http://localhost:9090

Pontos finais
GET    /api/pets
GET    /api/pets/{id}
GET    /api/pets/status/{status}
POST   /api/pets
PUT    /api/pets/{id}
DELETE /api/pets/{id}
Estrutura
src/main/java/br/com/PetRegistry/
├── controller/
├── service/
├── repository/
├── model/
└── DTORequests/
Roteiro
Autenticação JWT
Carregar de imagens
Paginação
Documentação Swagger
Contato
Nicolas Eduardo
Email: nicoedu123@gmail.com
GitHub: @XXnicor

Projeto desenvolvido a partir do desafio do DevMagro, aplicando conceitos de Spring Boot e boas práticas de desenvolvimento.
