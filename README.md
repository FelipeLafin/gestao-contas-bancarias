# Gestão de Contas Bancárias - API REST com Spring Boot

## Descrição
Este projeto implementa uma API REST para gerenciamento de contas bancárias utilizando Spring Boot, Spring Data JPA e banco de dados em memória H2.  
Permite criar, consultar, atualizar saldo (depósito e saque) e ativar/desativar contas.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (banco em memória)
- Maven

---

## Funcionalidades

- Criar nova conta bancária
- Listar todas as contas
- Buscar conta por ID
- Depositar valor na conta
- Sacar valor da conta
- Ativar ou desativar conta

---

## Como Rodar

### Pré-requisitos
- Java 17 ou superior instalado
- Maven instalado

### Passos para rodar

1. Clone o repositório:

```bash
git clone https://github.com/FelipeLafin/gestao-contas-bancarias.git
cd caminho-para-o-arquivo/gestao-contas-bancarias

```

2. Instale as dependencias:

```bash
mvn clean install
```

3. Iniciando o projeto:
 - Abra um novo terminal e digite isto para executar:

```bash
mvn spring-boot:run
```
 - Caso deseje abrir o menu, procure pelo arquivo java\com\example\client e rode o arquivo MenuPerguntas.java *OU* rode o arquivo:
 ```bash
 java src\main\java\com\example\client\MenuPerguntas.java
 ```

4. Iniciando o banco:
- Acesse o console do banco H2 (opcional, para ver tabelas e dados) no seu navegador usando:

```bash
http://localhost:8080/h2
```
5. EXTRA:
- É possivel usar um menu que fiz no terminal!

Use os parâmetros:

JDBC URL: jdbc:h2:mem:meubanco

Usuário: sa

Senha: (deixe em branco)