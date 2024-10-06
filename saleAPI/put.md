### Update sale
**Method** : `PUT`

**URL** : `/api/sales/{id}`

**Path Parameters** :

- id: Long (ID of the sale to update)

**Success Response** :

- Status Code: `200 OK`

**Request Body** : 

- {
  "paymentMethod": "string", 
  "saletimestamp": "string (ISO 8601 format)", 
  "totalPrice": "bigdecimal", 
  "ticketIds": ["Long"], 
  "userId": "Long" 
}

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

__Condition: If the sale does not exist__

Code: ```404 Not Found``` 

Message: ```Sale not found with given ID```

Content: ```{"timestamp": "2024-10-06T18:44:49.593+00:00",
    "status": 500,
    "error": "Internal Server Error",}```

__Condition: If the userID does not exist__

Code: ```404 Not Found``` 

Message: ```Sale not found with given ID```

Content: ```{"timestamp": "2024-10-06T18:44:49.593+00:00",
    "status": 500,
    "error": "Internal Server Error",}```
