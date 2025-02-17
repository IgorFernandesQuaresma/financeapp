# FinanceApp

## Descrição
O FinanceApp é uma aplicação desenvolvida com Java Spring Boot para o gerenciamento de finanças pessoais. O objetivo é permitir que os usuários criem contas, movimentem valores, categorizem suas transações e acompanhem lançamentos recorrentes.

Atualmente, a aplicação possui funcionalidades para gerenciar usuários, permitindo operações como criação, atualização e consulta de dados de usuários. Futuramente, a aplicação será expandida para incluir funcionalidades relacionadas a contas bancárias, movimentações financeiras, categorias de transações e lançamentos recorrentes.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Spring Security (futuro)
- Thymeleaf (futuro)

## Endpoints Implementados (Versão Atual)

### Usuários
- **GET /usuarios**: Retorna todos os usuários cadastrados.
- **GET /usuarios/{id}**: Retorna um usuário específico, identificado pelo ID.
- **GET /usuarios/nome/{nome}**: Retorna todos os usuários cujo nome contenha o termo fornecido (ignora case-sensitive).
- **POST /usuarios**: Cria um novo usuário.
- **PATCH /usuarios/{id}**: Atualiza o nome, email e senha de um usuário.
- **DELETE /usuarios/{id}**: Deleta um usuário pelo ID.

## Conta

- **GET /contas**: Retorna todas as contas cadastradas.
- **GET /contas/{id}**: Retorna uma conta específica, identificada pelo ID.
- **GET /contas/usuario/{usuarioId}**: Retorna todas as contas associadas a um usuário pelo seu ID.
- **GET /contas/usuario/{usuarioId}/nome/{nome}**: Retorna todas as contas associadas a um usuário e que contenham o nome fornecido (ignora case-sensitive).
- **POST /contas/usuario/{usuarioId}**: Cria uma nova conta associada a um usuário.
- **PATCH /contas/conta/{id}**: Atualiza os dados de uma conta existente (atualmente, apenas o nome pode ser alterado).


## Próximas Funcionalidades

### Movimentação
Entidade Movimentação: Implementar movimentações financeiras (entrada e saída), com controle de parcelas e categoria.

### Categoria
Entidade Categoria: Categorizar transações financeiras como "entrada" ou "saída" para melhor controle financeiro.

### Lançamentos Recorrentes
Entidade Lançamento Recorrente: Implementar a criação e controle de lançamentos recorrentes, como parcelas mensais ou anuais.

### Cartão de Crédito
Entidade Cartão de Crédito: Criar uma funcionalidade para controlar cartões de crédito, com informações sobre limite, fechamento e vencimento.
