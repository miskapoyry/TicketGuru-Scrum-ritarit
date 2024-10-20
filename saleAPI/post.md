### Create sale
**Method** : `POST`

**URL** : `/api/sales`

**Request Body** :
```json
{
  "userId": "Long",
  "paymentMethod": "string",
  "tickets": [
    {
      "eventId": "Long",
      "ticketTypeId": "Long",
      "quantity": "integer",
      "used": "boolean"
    }
  ]
}
```

**Succes Response** :

- Status Code: 200 OK

**Body** : 

- {
  "saleId": "Long",
  "paymentMethod": "string",
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
    "saleId": 5,
    "paymentMethod": "Cash",
    "totalPrice": 99.99,
    "saleTimestamp": "2024-10-20T17:59:01.523+00:00",
    "userId": 4,
    "tickets": [
        {
            "ticketId": 6,
            "ticketNumber": "ad183d61-11c9-4e4e-8d61-810b545153b9",
            "eventId": 1,
            "ticketTypeId": 1,
            "saleId": 5,
            "saleTimestamp": "2024-10-20T17:59:01.523+00:00",
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

Condition: If the paymentMethod is empty

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