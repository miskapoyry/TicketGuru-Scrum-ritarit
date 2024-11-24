### Update sale
**Method** : `PUT`

**URL** : `/api/sales/{id}`

**Path Parameters** :

- id: Long (ID of the sale to update)

**Success Response** :

- Status Code: `200 OK`

**Request Body** : 

- {
  "paymentMethodId": "Long",
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
    "paymentMethodId": 1,
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


**Example outcome** : 

```json
{
  "saleId": 3,
  "paymentMethodId": 1,
  "totalPrice": 199.98,
  "saleTimestamp": "2024-11-24T15:06:25.817+00:00",
  "userId": 1,
  "tickets": [
    {
      "ticketId": 4,
      "ticketNumber": "63be3b15-3696-45b8-a6ef-120ea0d7d708",
      "eventId": 1,
      "eventName": "Tech Conference 2024",
      "ticketTypeId": 1,
      "ticketTypeName": "Regular",
      "saleId": 3,
      "saleTimestamp": "2024-11-24T15:03:03.000+00:00",
      "usedTimestamp": null,
      "quantity": 1,
      "price": 99.99,
      "used": false
    },
    {
      "ticketId": 5,
      "ticketNumber": "e4e5c60f-6305-4530-90c6-86ce136081a8",
      "eventId": 1,
      "eventName": "Tech Conference 2024",
      "ticketTypeId": 1,
      "ticketTypeName": "Regular",
      "saleId": 3,
      "saleTimestamp": "2024-11-24T15:03:03.000+00:00",
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

__Condition: If the paymentMethodId is null__

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