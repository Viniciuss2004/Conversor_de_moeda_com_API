# 💱 Conversor de Moedas

Este é um projeto em Java que permite converter valores entre diferentes moedas utilizando a API da [ExchangeRate-API](https://www.exchangerate-api.com/). O sistema interage com o usuário via terminal e realiza a conversão com base na cotação atual da moeda selecionada.

## 📌 Funcionalidades

- Conversão entre as seguintes moedas:
  - Dólar (USD) ⟷ Peso Argentino (ARS)
  - Dólar (USD) ⟷ Real Brasileiro (BRL)
  - Dólar (USD) ⟷ Peso Colombiano (COP)
- Consulta da cotação em tempo real via API
- Exibição das datas de última e próxima atualização da taxa de câmbio
- Validação de entrada e tratamento de exceções personalizadas

## 🧩 Tecnologias e Bibliotecas Utilizadas

- Java 17+
- API HTTP Client (java.net.http)
- [Gson](https://github.com/google/gson) para desserialização de JSON
- ExchangeRate-API (requisição HTTP)
