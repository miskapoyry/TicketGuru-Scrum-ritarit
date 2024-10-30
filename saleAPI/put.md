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
  "userId": "Long", 
  "tickets": [
    {
      "eventId": "Long",
      "ticketTypeId": "Long",
      "quantity": "integer",
      "used": "boolean"
    }
  ]
}


```json
{
    "paymentMethod": "Cashy",
    "userId": 4,
    "tickets": [
        {
            "eventId": 1,
            "ticketTypeId": 1,
            "quantity": 1,
            "used": false
        }
    ]
}
```
```json
{
    "paymentMethod": "Cashy",
    "userId": 1,
    "tickets": [
        {
            "eventId": 1,
            "ticketTypeId": 1,
            "quantity": 1,
            "used": false
        }
    ]
}
```

**Example outcome** : 

```json
{
    "saleId": 2,
    "paymentMethod": "Cashy",
    "totalPrice": 99.99,
    "saleTimestamp": "2024-10-20T22:41:48.973+00:00",
    "userId": 1,
    "tickets": [
        {
            "ticketId": 2,
            "ticketNumber": "ad183d61-11c9-4e4e-8d61-810b545153b9",
            "eventId": 1,
            "ticketTypeId": 1,
            "saleId": 2,
            "saleTimestamp": "2024-10-20T17:59:01.000+00:00",
            "usedTimestamp": null,
            "quantity": 1,
            "price": 99.99,
            "used": false
        }
    ]
}
```

**Error Responses** :

__Condition: If the sale does not exist__

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "Sale not found with given ID",
    "status": 404,
    "timestamp": "2024-10-21T01:02:15.3668568",
    "path": "uri=/api/sales/4"
}
```

__Condition: If the userID does not exist__

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "User not found with given ID",
    "status": 404,
    "timestamp": "2024-10-21T01:19:41.1725262",
    "path": "uri=/api/sales/2"
}
```

__Condition: If the userID is null__

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "UserID is required",
    "status": 400,
    "timestamp": "2024-10-21T01:20:38.1319871",
    "path": "uri=/api/sales/2"
}
```

__Condition: If the paymentMethod is null__

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Payment method is required",
    "status": 400,
    "timestamp": "2024-10-21T01:37:37.3805961",
    "path": "uri=/api/sales/2"
}
```