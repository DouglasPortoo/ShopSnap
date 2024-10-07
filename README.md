# ShopSnap

## Visão Geral

**ShopSnap** é uma aplicação de e-commerce onde o usuário pode realizar cadastro, login, navegar por um carrinho de compras, finalizar pedidos e gerenciar seu perfil, incluindo o envio de fotos pela câmera ou galeria do dispositivo.

Este projeto foi desenvolvido com foco em fornecer uma experiência completa de um sistema de compras, implementando tanto funcionalidades de back-end quanto de front-end para construir uma solução robusta e eficiente.

## Funcionalidades

- Cadastro e login de usuários.
- Fluxo completo de carrinho de compras e fechamento de pedidos.
- Tela de perfil do usuário, com opção de envio de foto via câmera ou galeria.
- Sistema de autenticação e autorização seguro utilizando tokens JWT.
- Armazenamento de imagens no Amazon S3.

## Tecnologias Utilizadas

### Back-End
- **Java** com **Spring Boot**
- **JPA** com **Hibernate** para mapeamento objeto-relacional.
- **MySQL** como banco de dados relacional.
- **JWT (JSON Web Tokens)** para autenticação e autorização.
- **Amazon S3** para armazenamento de imagens.
- **SMTP da Google** para envio de emails.
- Implantação na nuvem usando a plataforma **Heroku**.

## Funcionalidades Técnicas

- **API REST**: Implementação de uma API RESTful para gerenciar as operações do usuário, pedidos e carrinho de compras.
- **Validação de Dados**: O projeto inclui regras de validação de dados no lado do servidor.
- **Tratamento de Exceções**: Implementação de um sistema robusto de tratamento de exceções para garantir a integridade dos dados.
- **Segurança**: Configuração de **CORS** e uso adequado do protocolo HTTP para garantir que as operações sejam seguras e compatíveis com os padrões da web.

## Requisitos do Sistema

- **Java 8+**
- **MySQL 5.7+**
- **Conta no Amazon AWS** (para integração com o S3)
- **Conta no Google** (para SMTP e envio de emails)
- **Heroku CLI** (para deployment na nuvem)
- **Maven** (para gerenciamento de dependências)
