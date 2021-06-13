## API Summary && FLow:


### 1. login Alice

Request:

POST http://localhost:8080/api/v1/user/login?username=Alice

Response:

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGljZSIsImlhdCI6MTYyMzU3MzM2OSwiZXhwIjoxNjIzNTgwNTY5fQ.ZDVuY5Y1CT1d_0voiY8YdoLIqZBATvzy14pYzyCEQPzwBNJ37RvNZqoWYDODwHRRKHqcrnfXARfB713O8mTQsA",
    "account": {
        "username": "Alice",
        "balance": 0.0
    },
    "loanInfo": []
}
```


### 2. Alice Topup 100

POST http://localhost:8080/api/v1/transaction/topup?amount=100

```
{
    "account": {
        "username": "Alice",
        "balance": 100.0
    },
    "transactionInfo": [],
    "loanInfo": []
}
```

Notes: put `token` in login response to request header `Authorization`

### 3. login Bob

POST http://localhost:8080/api/v1/user/login?username=Bob

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCb2IiLCJpYXQiOjE2MjM1NzM1MjIsImV4cCI6MTYyMzU4MDcyMn0.VVe-5yE6YnSfVoB4fUs8Tr8882gw0UWODuqSP_YQ06sCKo8a2qKLziN52tBusk5Qcm5BK8ve3KWobj2MqSKpnQ",
    "account": {
        "username": "Bob",
        "balance": 0.0
    },
    "loanInfo": []
}
```

### 4. Bob Topup 80

POST http://localhost:8080/api/v1/transaction/topup?amount=80

```
{
    "account": {
        "username": "Bob",
        "balance": 80.0
    },
    "transactionInfo": [],
    "loanInfo": []
}
```

Notes: put `token` in login response to request header `Authorization`


### 5. Bob Pay Alice 50

POST http://localhost:8080/api/v1/transaction/pay?recipient=Alice&amount=50

```
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

Notes: put `token` in login response to request header `Authorization`

### 6. Bob Pay Alice 100

POST http://localhost:8080/api/v1/transaction/pay?recipient=Alice&amount=100

```
{
    "account": {
        "username": "Bob",
        "balance": 0.0
    },
    "preLoan": {
        "loanId": null,
        "borrowerName": null,
        "lenderName": null,
        "amount": 0.0
    },
    "transactionInfo": {
        "transactionId": "963d0d30-dc04-448a-808e-ebc31002326b",
        "payerName": "Bob",
        "recipientName": "Alice",
        "amount": 30.0
    },
    "postLoan": {
        "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
        "borrowerName": "Bob",
        "lenderName": "Alice",
        "amount": 70.0
    }
}
```

Notes: put `token` in login response to request header `Authorization`

### 7. Bob Topup 30

POST http://localhost:8080/api/v1/transaction/topup?amount=30

```
{
    "account": {
        "username": "Bob",
        "balance": 0.0
    },
    "transactionInfo": [],
    "loanInfo": [
        {
            "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
            "borrowerName": "Bob",
            "lenderName": "Alice",
            "amount": 40.0
        }
    ]
}
```

Notes: put `token` in login response to request header `Authorization`

### 8. login Alice

POST http://localhost:8080/api/v1/user/login?username=Alice

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGljZSIsImlhdCI6MTYyMzU3NDAyNiwiZXhwIjoxNjIzNTgxMjI2fQ.qxEPFF81soRKAVNBeL0sCRZiMFsi0PvY8e_R9awa3BexuVbW4_71OGh0G5g0qv4t8XfXbVj50xVM00v4f711Ow",
    "account": {
        "username": "Alice",
        "balance": 210.0
    },
    "loanInfo": [
        {
            "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
            "borrowerName": "Bob",
            "lenderName": "Alice",
            "amount": 40.0
        }
    ]
}
```

### 9. Alice Pay Bob 30

POST http://localhost:8080/api/v1/transaction/pay?recipient=Bob&amount=30

```
{
    "account": {
        "username": "Alice",
        "balance": 210.0
    },
    "preLoan": {
        "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
        "borrowerName": "Bob",
        "lenderName": "Alice",
        "amount": 40.0
    },
    "transactionInfo": {
        "transactionId": null,
        "payerName": null,
        "recipientName": null,
        "amount": 0.0
    },
    "postLoan": {
        "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
        "borrowerName": "Bob",
        "lenderName": "Alice",
        "amount": 10.0
    }
}
```

Notes: put `token` in login response to request header `Authorization`

### 10. login Bob

POST http://localhost:8080/api/v1/user/login?username=Bob

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCb2IiLCJpYXQiOjE2MjM1NzQyMDMsImV4cCI6MTYyMzU4MTQwM30.9TTqPcIZovw460WWS4iSKktGqMsG0SA00gMlm2atMdp6Wy7yeF4DwhjqKOOBj6ZaWrFupNncLL8fXOFm_yKasw",
    "account": {
        "username": "Bob",
        "balance": 0.0
    },
    "loanInfo": [
        {
            "loanId": "f6a4419e-c56a-4dd6-959f-a51a7e6420d8",
            "borrowerName": "Bob",
            "lenderName": "Alice",
            "amount": 10.0
        }
    ]
}
```

### 11. Bob Topup 100

POST http://localhost:8080/api/v1/transaction/topup?amount=100

```
{
    "account": {
        "username": "Bob",
        "balance": 90.0
    },
    "transactionInfo": [
        {
            "transactionId": "28f2cf14-2c79-4378-b3d6-e7228722d206",
            "payerName": "Bob",
            "recipientName": "Alice",
            "amount": 10.0
        }
    ],
    "loanInfo": []
}
```

Notes: put `token` in login response to request header `Authorization`

### 12. login Alice

POST http://localhost:8080/api/v1/user/login?username=Alice

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGljZSIsImlhdCI6MTYyMzU3NDQwMCwiZXhwIjoxNjIzNTgxNjAwfQ.K-g1rBz8A4kggVDJw7k8rE63nkcksgOarzhlE3uPbU4T9buKNYcqUtMalstGCb-I7blFQVf6N2fFp_3KDXSiVg",
    "account": {
        "username": "Alice",
        "balance": 220.0
    },
    "loanInfo": []
}
```


