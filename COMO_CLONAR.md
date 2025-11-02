### Instruções para clonar o repositório localmente
1. Use `git clone` normalmente, ou use seu método de escolha para clonar o repositório
2. No seu clone local, encontre o arquivo `/estoque-backend/src/main/resources/application-template.properties`
3. Crie uma cópia desse arquivo e mude o nome para `application.properties`
4. Dentro do arquivo renomeado, complete os campos `spring.datasource.username` e `spring.datasource.password` com seu usuário e senha do MySQL Server

### Pré-requisitos para executar a aplicação
* Java 21 SDK
* MySQL Server 8
* Ter criado o banco de dados `db_stock` no MySQL Server
* Caso tenha instalado o MySQL Server, mas ainda não criou o banco, pode criá-lo com o seguinte script:
```SQL
DROP DATABASE IF EXISTS db_stock;
CREATE DATABASE db_stock;
```
