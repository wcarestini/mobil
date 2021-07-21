# Mobil

API de mobilidade urbana integrada com a API Data Poa.

<hr>

## 🛠 Tecnologias
As seguintes ferramentas foram usadas na construção desta API:

1. Java 8
2. Swagger, para documentação da api
3. Spring, para criação da API REST
4. JPA, usado para os relacionamentos com o banco e suas operações
5. Banco de Dados (PostgreSQL)
6. Lombok, utilizado para facilitar o desenvolvimento e reduzir as linhas de código

<hr>

## 📃 Documentação
A documentação foi feita utilizando o [swagger](https://swagger.io/). <br/>
Com a API rodando, basta acessar http://localhost:8080/swagger-ui.html;

<hr>

## 🗄️ Banco de dados
### Diagrama entidade relacionamento

![Diagrama entidade relacionamento](https://github.com/wcarestini/mobil/blob/main/assets/img/diagrama-entidades-mobil.png?raw=true)

### Instruções

Para utilizar a API você precisa ter uma instância de um banco de dados, caso não tenha, você pode criar uma instância de um banco postgres no [ElephantSQL](https://www.elephantsql.com/).
O projeto pode ser rodado na IDE, precisando ter um arquivo com as variaveis de ambiente descritas abaixo.

#### Configuração da conexão com o banco

Com o banco criado, adicione um arquivo env.yml na raiz do projeto, seguindo o exemplo abaixo:
```yml
DB_SERVER: "server.db.com"
DB_USERNAME: "username"
DB_PASSWORD: "password"
```

<hr>

## ▶️ Endpoints
### - Integração

#### Copiar Linhas: POST http://localhost:8080/integration/copy-lines

#### Copiar Itinerários: POST http://localhost:8080/integration/copy-itinerary/{idLinha}

### - Linhas

#### Busca Linhas por nome: GET http://localhost:8080/line/{nomeLinha}

#### Salva/atualiza Linha: POST http://localhost:8080/line
    payload = {
        "codigo": "string",
        "id": 0,
        "nome": "string"
    }

#### Deletar Linha: DELETE http://localhost:8080/line/{idLinha}

### - Itinerários

#### Salva/atualiza itinerário: POST http://localhost:8080/itinerary
    payload = {
        "codigo": "string",
        "coordenadas": [
        {
        "lat": 0,
        "lng": 0
        }
        ],
        "idlinha": 0,
        "nome": "string"
    }

#### Busca Itinerario por código: GET http://localhost:8080/itinerary/{codigo}

#### Deletar Itinerario: DELETE http://localhost:8080/itinerary/{idItinerario}
