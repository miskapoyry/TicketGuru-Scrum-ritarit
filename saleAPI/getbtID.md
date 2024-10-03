### Get salebyId

**Method** : `GET`

**URL** : `/api/sales/{id}`

**Path Parameters** : 
- id: Long (ID of the sale to retrieve)

**Succes Response** :

- Status Code: `200 OK`

**Request Body** : 

- { "paymentMethod": "string", "saletimestamp": "string (ISO 8601 format)", "totalPrice": "bigdecimal", "
"ticketIds": ["Long"], "appUser": { "id": "Long" } }

```json
[
    {
        "saleId": 1,
        "paymentMethod": "Credit Card",
        "totalPrice": 199.98,
        "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
        "userId": 1,
        "ticketIds": []
    }
]
```

**Error Responses** :

Condition: If the event does not exist

Code: ```404 Not Found``` 

Content: ```{}```