Payments API

This REST API enables fund transfer from one account to another account.

Technologies Used:
SpringBoot,JPA,JWT (OAuth2),MySql

How to Run?
Clone the project.
Import Schema from binaries/schema.sql
Change credentials of database in application.properties

Assumptions:
Pre-defined accounts available in database.

To Test the API

1. localhost:8080/payments/v1/ping

    Check for response "I am up"

Generate Bearer Token
2. localhost:8080/oauth/token

Request Body
grant_type:password
password:secret
username:monese

Basic Auth Headers
password:secret
username:monese

Sample Response

{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwiZXhwIjoxNTUzOTgzNDA1LCJ1c2VyX25hbWUiOiJtb25lc2UiLCJqdGkiOiI4YWJmYjM0Ny0zMTU3LTQzN2YtYTJhZC0yN2RiM2E1NjljNzkiLCJjbGllbnRfaWQiOiJtb25lc2UiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.aR78qYo1qdfJ5hpI7jwdCp6zrcvnU2ldQspmyb0mp2k",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwidXNlcl9uYW1lIjoibW9uZXNlIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjhhYmZiMzQ3LTMxNTctNDM3Zi1hMmFkLTI3ZGIzYTU2OWM3OSIsImV4cCI6MTU1NjU3MTgwNSwianRpIjoiYTQ1Mzg0NTMtY2Q4NS00OTNjLTg2ZmItN2E0NmU3MzAyYTFiIiwiY2xpZW50X2lkIjoibW9uZXNlIn0.viUbbj8JcXuCO-oLx8MxlI80ufpMI7mKl7pz_P0MORo",
    "expires_in": 3599,
    "scope": "read write",
    "jti": "8abfb347-3157-437f-a2ad-27db3a569c79"
}

Note: Token expires in 6 minutes, would need to generate token again.

3. localhost:8080/payments/v1/transactions/transferFund

Use Bearer token generated above to initiate the transaction

Sample Request Body
{
"fromAccount":"9638527411",
"toAccount":"9638527410",
"amount":250
}

Sample Response Body
{
    "status": "S",
    "message": "SUCCESS",
    "code": 200,
    "fromAccount": "9638527411",
    "toAccount": "9638527410"
}

4.localhost:8080/payments/v1/transactions/history?accountNumber=9638527410&page=0&size=12

Use Bearer token generated above to initiate the transaction
query-params:
    accountNumber=9638527410
    page=0
    size=12

Sample Response
{
    "content": [
        {
            "id": 1,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553974433000
        },
        {
            "id": 2,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553974518000
        },
        {
            "id": 3,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553974689000
        },
        {
            "id": 4,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553974906000
        },
        {
            "id": 5,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553975059000
        },
        {
            "id": 6,
            "fromAccount": "9638527410",
            "toAccount": "9638527411",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553975293000
        },
        {
            "id": 7,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 50000,
            "transactionStatusCode": "I",
            "transactionStatusMessage": "INITIATED",
            "transactionOriginationTime": 1553975349000
        },
        {
            "id": 8,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 50000,
            "transactionStatusCode": "F",
            "transactionStatusMessage": "FAILED",
            "transactionOriginationTime": 1553975500000
        },
        {
            "id": 9,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553975517000
        },
        {
            "id": 10,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 500,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553975579000
        },
        {
            "id": 11,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 2,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553976784000
        },
        {
            "id": 12,
            "fromAccount": "9638527411",
            "toAccount": "9638527410",
            "amount": 2,
            "transactionStatusCode": "S",
            "transactionStatusMessage": "SUCCESS",
            "transactionOriginationTime": 1553977340000
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 12,
    "size": 12,
    "number": 0,
    "numberOfElements": 12,
    "sort": null,
    "first": true
}