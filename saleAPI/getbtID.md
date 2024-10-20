### Get salebyId

**Method** : `GET`

**URL** : `/api/sales/{id}`

**Path Parameters** : 
- id: Long (ID of the sale to retrieve)

**Succes Response** :

- Status Code: `200 OK`

```json
[
    {
    "saleId": 1,
    "paymentMethod": "Credit Card",
    "totalPrice": 199.98,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "userId": 1,
    "tickets": [
        {
            "ticketId": 1,
            "ticketNumber": "TICKET-1001",
            "eventId": 1,
            "ticketTypeId": 1,
            "saleId": 1,
            "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
            "usedTimestamp": null,
            "quantity": 1,
            "price": 99.99,
            "used": false
        },
        {
            "ticketId": 2,
            "ticketNumber": "TICKET-1002",
            "eventId": 1,
            "ticketTypeId": 1,
            "saleId": 1,
            "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
            "usedTimestamp": null,
            "quantity": 1,
            "price": 99.99,
            "used": false
        }
    ]
}
]
```

**Error Responses** :

Condition: If the saleID does not exist

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "Sale not found with given ID: 33",
    "status": 404,
    "timestamp": "2024-10-20T20:54:58.5194843",
    "path": "uri=/api/sales/33"
}
```