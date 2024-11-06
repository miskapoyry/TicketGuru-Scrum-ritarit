### Create a eventTicketType

**Method**: `POST`

**URL**: `/api/eventTicketTypes`

**Request Body**:

```json
    {
        "eventId": 1,
        "ticketTypeId": 2,
        "ticketQuantity": 100,
        "price": 50.00
    }
```

**Successful Response**:

- Status Code: `201 Created`

**Response Body**:

```json
    {
        "eventTicketTypeId": 7,
        "eventId": 1,
        "ticketTypeId": 2,
        "ticketQuantity": 100,
        "price": 50.0,
        "eventName": "Tech Conference 2024",
        "ticketTypeName": "VIP"
    }
```

**Error Responses** :

**Condition: If eventId does not exist**

Code: `404 Not Found` 

Error Example: If eventId does not exist

```json
{
"message":"Event not found with given ID",
"status":404,
"timestamp":"2024-11-06T00:29:30.5718156",
"path":"uri=/api/eventTicketTypes"
}
```
**Condition: If ticketTypeId does not exist**

Code: `404 Not Found` 

Error Example: If ticketTypeId does not exist

```json
{
"message":"TickettypeId not found with given ID",
"status":404,
"timestamp":"2024-11-06T00:29:30.5718156",
"path":"uri=/api/eventTicketTypes"
}
```

**Condition: Price is zero or less**

Code: `400 Bad Request`

Error Example:

```json
{
"message":"Price must be greater than zero",
"status":400,
"timestamp":"2024-11-06T00:33:31.1474955",
"path":"uri=/api/eventTicketTypes"
}
```

**Condition: Ticket quantity must be greater than zero**

Code: `400 Bad Request`

Error Example:

```json
{
"message":"Ticket quantity must be greater than zero",
"status":400,
"timestamp":"2024-11-06T00:25:41.0582127",
"path":"uri=/api/eventTicketTypes/1"
}
```

