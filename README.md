# mini-bank-payment-system
This application is an simple example flow of bank debit and credit workflow. And it 
supports login, topup and pay api functoins.

## Develop Environment
- JDK11
- Mysql 8.0.25
- Maven 3.8.1
- IntelliJ IDEA Ultimate 2020.3


## Code Structure

source tree
```
.
├── auth
│   ├── jwt
│   │   ├── JwtInterceptor.java
│   │   ├── JwtTokenProvider.java
│   │   └── JwtUser.java
│   └── UserContext.java
├── commons
│   ├── ResponseHelpers.java
│   └── utils
│       └── IdGenerator.java
├── config
│   └── InterceptorConfig.java
├── controllers
│   ├── AccountController.java
│   ├── responses
│   │   ├── AccountData.java
│   │   ├── LoanData.java
│   │   ├── LoginResponse.java
│   │   ├── PayResponse.java
│   │   ├── TopupResponse.java
│   │   └── TransactionData.java
│   └── TransactionController.java
├── entities
│   ├── Account.java
│   ├── Auditable.java
│   ├── Loan.java
│   └── Transaction.java
├── exceptions
│   ├── AccountNotFoundException.java
│   ├── BusinessException.java
│   ├── InvalidJwtTokenException.java
│   ├── NotFoundException.java
│   └── RequestHeaderNotFoundException.java
├── MiniBankPaymentSystemApplication.java
├── repositories
│   ├── AccountRepository.java
│   ├── LoanRepository.java
│   └── TransactionRepository.java
└── services
    ├── AccountService.java
    ├── LoanService.java
    └── TransactionService.java

```

DB Schema

<img src="docs/static/minibank_db.png" width="90%"/>

UML Class Diagram

<img src="docs/static/minibank_uml.png" width="90%"/>

## Limitations & Future Work
Currently user need to setup their own jdk and mysql to try this application. Later will add
docker file and docker compose file for this repo.

## Usages
You can start to try by running
- setup JDK11 and mysql 8, create `minibank` database 
-  open the project in IntelliJ to run or using belwo maven command
- `mvn spring-boot:run` to start the spring boot application
- use postman to try below restful api and need to add the token in request header `Authorization`.
- for whole work flow testing result, please refer to `docs/work_flow.md`

API Example:

1. login BOb

- jwt token is used for other rest api
- account shows the current account details
- loanInfo shows all the loans related to current user

```
POST
POST http://localhost:8080/api/v1/user/login?username=Bob
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCb2IiLCJpYXQiOjE2MjM1NzM1MjIsImV4cCI6MTYyMzU4MDcyMn0.VVe-5yE6YnSfVoB4fUs8Tr8882gw0UWODuqSP_YQ06sCKo8a2qKLziN52tBusk5Qcm5BK8ve3KWobj2MqSKpnQ",
    "account": {
        "username": "Bob",
        "balance": 0.0
    },
    "loanInfo": []
}
```

2. Bob Topup

- account shows the current account details 
- transactionInfo shows the generated transaction by topup action
- loanInfo shows all the loans related to current user

```
POST 
http://localhost:8080/api/v1/transaction/topup?amount=80
{
    "account": {
        "username": "Bob",
        "balance": 80.0
    },
    "transactionInfo": [],
    "loanInfo": []
}
``` 

3. Bob Pay Alice

- account shows the current account details 
- preLoan shows existing loanInfo between two persons
- transactionInfo shows the generated transaction by pay action
- postLoan shows the new generated loanInfo by pay action

```
POST 
http://localhost:8080/api/v1/transaction/pay?recipient=Alice&amount=50

{
    "account": {
        "username": "Bob",
        "balance": 30.0
    },
    "preLoan": {
        "loanId": null,
        "borrowerName": null,
        "lenderName": null,
        "amount": 0.0
    },
    "transactionInfo": {
        "transactionId": "a4be87e0-7cd1-47e4-adf5-dd55400eb3aa",
        "payerName": "Bob",
        "recipientName": "Alice",
        "amount": 50.0
    },
    "postLoan": {
        "loanId": null,
        "borrowerName": null,
        "lenderName": null,
        "amount": 0.0
    }
}
``` 



