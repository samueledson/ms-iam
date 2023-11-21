Explicação da estrutura:

- application: essa pasta contém as interfaces de serviço (portas) que definem os casos de uso do sistema. Por exemplo, UserAuthenticationService define as operações relacionadas à autenticação de usuários e UserService define as operações relacionadas ao gerenciamento de usuários.

- domain: essa pasta contém o modelo de domínio e os serviços do domínio. O pacote model contém as entidades do domínio, como User, e as interfaces de repositório, como UserRepository. O pacote service contém as implementações dos serviços do domínio, como UserAuthenticationDomainService e UserDomainService. A pasta exception contém exceções personalizadas relacionadas ao domínio.

- infrastructure: essa pasta contém os adaptadores para a infraestrutura externa. O pacote persistence contém as implementações de repositório, como UserRepositoryImpl, que lidam com a persistência dos usuários. O pacote security contém adaptadores relacionados à segurança, como SecurityAdapter, que lida com a autenticação e autorização.

- presentation: essa pasta contém as classes relacionadas à apresentação da API. O pacote controller contém os controladores da API, como UserController, que definem as rotas e os métodos para lidar com as solicitações HTTP. O pacote dto contém os objetos de transferência de dados (DTO) usados para transportar dados entre a camada de controle e a camada de aplicação.

- MsUsersApplication.java: essa é a classe principal do aplicativo Spring Boot, responsável por iniciar a aplicação.

```
src/main/java
└── com
    └── exemplo
        └── meuapp
            ├── application
            │   ├── UserAuthenticationService.java
            │   └── UserService.java
            ├── domain
            │   ├── model
            │   │   ├── User.java
            │   │   └── UserRepository.java
            │   ├── service
            │   │   ├── UserAuthenticationDomainService.java
            │   │   └── UserDomainService.java
            │   └── exception
            │       └── CustomException.java
            ├── infrastructure
            │   ├── persistence
            │   │   └── UserRepositoryImpl.java
            │   └── security
            │       └── SecurityAdapter.java
            ├── presentation
            │   ├── controller
            │   │   └── UserController.java
            │   └── dto
            │       └── UserDTO.java
            └── MeuAppApplication.java
```