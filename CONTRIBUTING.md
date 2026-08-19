# Contribuindo com o Hanei FinanceHub API

## Fluxo de trabalho

1. Crie uma branch a partir de `main`: `feature/nome-da-feature` ou `fix/nome-do-bug`
2. Faça commits seguindo [Conventional Commits](https://www.conventionalcommits.org/):
    - `feat: adiciona parser de OFX`
    - `fix: corrige arredondamento no TransferBalanceUseCase`
    - `docs: atualiza README`
    - `test: adiciona testes para HealthScoreCalculator`
3. Garanta que `./mvnw verify` passa localmente (testes + cobertura mínima de 80%)
4. Abra um Pull Request para `main` — revisão de código é obrigatória antes do merge

## Padrões de código

- Camada `domain/` nunca depende de Spring, MongoDB ou qualquer framework
- Valores monetários sempre em `BigDecimal` com `RoundingMode.HALF_EVEN` — nunca `float`/`double`
- Toda regra de negócio nova precisa de teste de unidade correspondente