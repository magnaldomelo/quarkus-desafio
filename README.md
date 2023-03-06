# Sistema de cotação do dolar

O sistema de cotação do dolar consome uma API externa do Banco Central do Brasil. 

# Tecnologias utilizadas

Foram desenvolvidos dois Microservices:

Microservice A: Consome a API de Consulta do Dolar do BCB. Desenvolvida em Java com o Framework Quarkus, provê um endpoint de consulta, aplicando regras de validação e posteriormente buscando
as informações de Cotação do Dollar no BCB.

curl --request GET \
  --url 'http://localhost:8181/api/v1/dolar_cotacao?data_cotacao=03-03-2023' \
  --header 'Content-Type: application/json'

Microservice B: Serve de Proxy para os recursos do Microservice A, mas também aplica regras de validação. Foi desenvolvida com a mesma Stack do Microservice A.

curl --request GET \
  --url 'http://localhost:8080/api/v1/cotacao?data_cotacao=03-03-2023' \
  --header 'Content-Type: application/json'
  
  
# swagger

O Swagger foi utilizado em ambos os Microservices, podendo ser acessado pelas url's:

Microservice A: 

http://localhost:8181/q/swagger-ui

Microservice B:

http://localhost:8080/q/swagger-ui

# Executar Aplicação

Caso deseje executar a aplicação de forma direta, sem o docker compose, basta executar o seguinte comando em suas respectivas pastas:

./mvnw quarkus:dev

# Testes

Foram desenvolvidos Testes Unitários apenas no Microservice A.
Também foram feitos testes de integração para aferição das Regras de Validação.





