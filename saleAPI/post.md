### Create sale
**Method** : `POST`

**URL** : `/api/sales`

**Request Body** :
```json
{
  "userId": 1,
  "paymentMethodId": 2,
  "tickets": [
    {
      "eventId": "1",
      "ticketTypeId": "1",
      "quantity": "2",
      "used": "false"
    }
  ]
}
```

**Succes Response** :

- Status Code: 200 OK

**Body** : 

- {
  "saleId": "Long",
  "paymentMethodId": "Long",
  "totalPrice": "bigdecimal",
  "saleTimestamp": "string (ISO 8601 format)",
  "userId": "Long",
  "tickets": [
    {
      "ticketId": "Long",
      "ticketNumber": "string",
      "eventId": "Long",
      "ticketTypeId": "Long",
      "saleId": "Long",
      "saleTimestamp": "string (ISO 8601 format)",
      "usedTimestamp": "string (ISO 8601 format)",
      "quantity": "integer",
      "price": "bigdecimal",
      "used": "boolean"
    }
  ]
}



```json
{
  "saleId": 3,
  "paymentMethodId": 2,
  "totalPrice": 199.98,
  "saleTimestamp": "2024-11-24T15:03:03.282+00:00",
  "userId": 1,
  "tickets": [
    {
      "ticketId": 4,
      "ticketNumber": "63be3b15-3696-45b8-a6ef-120ea0d7d708",
      "eventId": 1,
      "eventName": null,
      "ticketTypeId": 1,
      "ticketTypeName": null,
      "saleId": 3,
      "saleTimestamp": "2024-11-24T15:03:03.282+00:00",
      "usedTimestamp": null,
      "quantity": 1,
      "price": 99.99,
      "used": false
    },
    {
      "ticketId": 5,
      "ticketNumber": "e4e5c60f-6305-4530-90c6-86ce136081a8",
      "eventId": 1,
      "eventName": null,
      "ticketTypeId": 1,
      "ticketTypeName": null,
      "saleId": 3,
      "saleTimestamp": "2024-11-24T15:03:03.282+00:00",
      "usedTimestamp": null,
      "quantity": 1,
      "price": 99.99,
      "used": false
    }
  ]
}

```

**Error Responses** :

Condition: If the userId isn't valid

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "User not found with given ID",
    "status": 404,
    "timestamp": "2024-10-20T21:05:14.8015115",
    "path": "uri=/api/sales"
}
```

Condition: EventID or TicketTypeID aren't matching or found

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "EventTicketType not found with given EventId and TicketTypeId",
    "status": 404,
    "timestamp": "2024-10-20T21:39:54.981637",
    "path": "uri=/api/sales"
}
```

Condition: If the userId is null

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "UserID is required",
    "status": 400,
    "timestamp": "2024-10-21T00:56:22.5136068",
    "path": "uri=/api/sales"
}
```

Condition: If the paymentMethodId is null

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Payment method is required",
    "status": 400,
    "timestamp": "2024-10-21T00:55:59.9115102",
    "path": "uri=/api/sales"
}
```

Condition: If the isUsed field is empty

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "isUsed field is required",
    "status": 400,
    "timestamp": "2024-10-21T00:55:38.6655414",
    "path": "uri=/api/sales"
}
```

Condition: If the quantity is less than one

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Quantity must be at least 1",
    "status": 400,
    "timestamp": "2024-10-21T00:55:18.9123098",
    "path": "uri=/api/sales"
}
```