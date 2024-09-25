
# Order Management Service

Este projeto é um serviço de gerenciamento de pedidos construído com Spring Boot, MongoDB e RabbitMQ. Ele permite a criação, consulta e agregação de pedidos de clientes.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção de aplicações Java.
- **MongoDB**: Banco de dados NoSQL para armazenamento de dados.
- **RabbitMQ**: Sistema de mensageria para comunicação assíncrona.
- **Docker**: Para containerização dos serviços.

## Estrutura do Projeto

- **Controller**: Contém os endpoints REST para interação com o serviço.
- **Service**: Contém a lógica de negócios.
- **Repository**: Interface para interação com o banco de dados MongoDB.
- **DTO**: Objetos de transferência de dados.
- **Listener**: Escuta eventos de criação de pedidos via RabbitMQ.

## Pré-requisitos

- Docker e Docker Compose instalados.
- Java 17 instalado.
- Maven instalado.

## Configuração

### Configuração do MongoDB

O MongoDB é configurado no arquivo [`application.properties`](command:_github.copilot.openRelativePath?%5B%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fresources%2Fapplication.properties%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22898d5fd6-e06b-4319-9976-52ae4a05afe3%22%5D "c:\Users\Lucas Rocha\WorkPast\Java Projects\orderms\src\main\resources\application.properties"):

```properties
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.auto-index-creation=true
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=btgpactualdb
spring.data.mongodb.username=admin
spring.data.mongodb.password=admin
```

### Configuração do RabbitMQ

O RabbitMQ é configurado no arquivo [`RabbitMqConfig.java`](command:_github.copilot.openRelativePath?%5B%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fconfig%2FRabbitMqConfig.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22898d5fd6-e06b-4319-9976-52ae4a05afe3%22%5D "c:\Users\Lucas Rocha\WorkPast\Java Projects\orderms\src\main\java\tech\buildrun\btgpactual\orderms\config\RabbitMqConfig.java"):

```java
@Configuration
public class RabbitMqConfig {
    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Declarable orderCreatedQueue(){
        return new Queue(ORDER_CREATED_QUEUE);
    }
}
```

## Executando a Aplicação

### Usando Docker Compose

Para iniciar os serviços MongoDB e RabbitMQ, execute:

```sh
docker-compose up
```

### Compilando e Executando a Aplicação

Para compilar e executar a aplicação, use os seguintes comandos:

```sh
mvn clean install
mvn spring-boot:run
```

## Endpoints

### Obter Pedidos por Cliente

```http
GET /order/customer/{customerId}
```

Parâmetros:
- [`customerId`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Frepository%2FOrderRepository.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A11%2C%22character%22%3A47%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fmodel%2FOrderEntity.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A18%2C%22character%22%3A17%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fservice%2FOrderService.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A13%2C%22character%22%3A56%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fcontroller%2FOrderController.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A26%2C%22character%22%3A107%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fservice%2Fcore%2FOrderServiceCore.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A46%2C%22character%22%3A57%7D%7D%5D%2C%22898d5fd6-e06b-4319-9976-52ae4a05afe3%22%5D "Go to definition"): ID do cliente.
- [`page`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fcontroller%2Fdto_controller%2FPaginationResponse.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A4%2C%22character%22%3A41%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fcontroller%2FOrderController.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A27%2C%22character%22%3A132%7D%7D%5D%2C%22898d5fd6-e06b-4319-9976-52ae4a05afe3%22%5D "Go to definition"): Número da página (opcional, padrão é 0).
- [`pageSize`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fc%3A%2FUsers%2FLucas%20Rocha%2FWorkPast%2FJava%20Projects%2Forderms%2Fsrc%2Fmain%2Fjava%2Ftech%2Fbuildrun%2Fbtgpactual%2Forderms%2Fcontroller%2FOrderController.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A28%2C%22character%22%3A137%7D%7D%5D%2C%22898d5fd6-e06b-4319-9976-52ae4a05afe3%22%5D "Go to definition"): Tamanho da página (opcional, padrão é 10).

### Exemplo de Resposta

```json
{
  "Summary": {
    "totalOnOrders": 1000.00
  },
  "data": [
    {
      "OrderId": 1,
      "customerID": 123,
      "total": 500.00
    }
  ],
  "pagination": {
    "page": 0,
    "PageSize": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

## Testes

Para executar os testes, use o comando:

```sh
mvn test
```

