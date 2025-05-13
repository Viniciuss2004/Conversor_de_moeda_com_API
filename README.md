# 💱 Conversor de Moedas

Este é um projeto em Java que permite converter valores entre diferentes moedas utilizando a API da [ExchangeRate-API](https://www.exchangerate-api.com/). O sistema interage com o usuário via terminal e realiza a conversão com base nas cotações mais recentes.

## 📌 Funcionalidades

- Conversão entre quaisquer moedas utilizando seus códigos (ex: USD, BRL, EUR, JPY)
- Consulta de cotações em tempo real via API
- Exibição da taxa de câmbio, valor convertido e datas de atualização
- Listagem completa das moedas disponíveis com base em uma moeda base
- Validação básica de entrada e tratamento de erros de conexão

## 🧩 Tecnologias e Bibliotecas Utilizadas

- Java 17+
- API HTTP Client (`java.net.http`)
- [Gson](https://github.com/google/gson) para desserialização de JSON
- ExchangeRate-API (requisições HTTP)
