# Hanei FinanceHub — API

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build](https://github.com/SEU_USUARIO/hanei-financehub-api/actions/workflows/ci.yml/badge.svg)](https://github.com/SEU_USUARIO/hanei-financehub-api/actions)

> "Engenharia de Software de Alta Precisão a Serviço da Prosperidade Financeira"

API backend do **Hanei FinanceHub**, uma plataforma de gestão de finanças pessoais construída sob rigorosos princípios de engenharia de software: precisão numérica, segurança de dados e Clean Architecture.

Repositório irmão: [`hanei-financehub-web`](https://github.com/SEU_USUARIO/hanei-financehub-web) (frontend React).

---

## Sumário

- [Stack Tecnológica](#stack-tecnológica)
- [Arquitetura](#arquitetura)
- [Como Rodar Localmente](#como-rodar-localmente)
- [Testes e Cobertura](#testes-e-cobertura)
- [Módulos do Sistema](#módulos-do-sistema)
- [Roadmap](#roadmap)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## Stack Tecnológica

| Camada | Tecnologias |
|---|---|
| Linguagem / Runtime | Java 21 LTS |
| Framework | Spring Boot 4.1.0 (Spring Framework 7) |
| Persistência | MongoDB Atlas — Decimal128, índices compostos, transações atômicas |
| Segurança | Spring Security + JWT (HMAC256) |
| Documentação | SpringDoc OpenAPI (Swagger UI em `/docs`) |
| Mapeamento | MapStruct |
| Build / Cobertura | Maven, JaCoCo (mínimo 80% nos serviços de domínio) |

## Arquitetura

Clean Architecture, com o domínio isolado de qualquer dependência de framework: