### Update sale
**Method** : `PUT`

**URL** : `/api/sales/{id}`

**Path Parameters** :

- id: Long (ID of the sale to update)

**Success Response** :

- Status Code: `200 OK`

**Request Body** : 

- - { "paymentMethod": "string", "saletimestamp": "string (ISO 8601 format)", "totalPrice": "bigdecimal", "
"ticketIds": ["Long"], "appUser": { "id": "Long" } }

```json
[
    {
    "saleId": 5,
    "paymentMethod": "Cash",
    "totalPrice": 99,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "userId": 1,
    "ticketIds": [
        1,
        2,
        3
    ]
}
]
```


```json
[
    {
    "saleId": 5,
    "paymentMethod": "Cash",
    "totalPrice": 12,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "userId": 1,
    "ticketIds": [
        1,
        2,
        3
    ]
}
]
```

**Content Example** : For the example above, when the totalPrice is updated.

**Error Responses** :

Condition: If the event does not exist

Code: ```404 Not Found``` 

Content: ```{}```
