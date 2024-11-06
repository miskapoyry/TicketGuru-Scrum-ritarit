### Update a eventTicketType

**Method**: `PUT`

**URL**: `/api/eventTicketType/{id}`

**Request Body**: 

- 

**Path Parameters**:

- `id`: 

**Success Response**:

- Status Code: `200 OK`

**Response Body**:

```json
{
    "eventTicketTypeId": 1,
    "event": {
    "eventId": 1
    },
    "ticketType": {
    "ticketTypeId": 1
    },
    "ticketQuantity": 100,
    "price": 100
}
```

**Error Responses** :

**Condition: If eventId does not exist**

Code: `404 Not Found` 

Error Example: If eventId does not exist

```json
{
"message":"Event not found with given ID: 10",
"status":404,
"timestamp":"2024-11-06T00:04:17.5243706",
"path":"uri=/api/eventTicketTypes/1"
}
```

**Condition: If eventTicketTypeId does not exist**

Code: `404 Not Found` 

Error Example: If eventTicketTypeId does not exist

```json
{
"message":"eventTicketTypeId not found with given ID: 10",
"status":404,
"timestamp":"2024-11-06T00:04:17.5243706",
"path":"uri=/api/eventTicketTypes/1"
}
```

**Condition: If ticketTypeId does not exist**

Code: `404 Not Found` 

Error Example: If ticketTypeId does not exist

```json
{
"message":"ticketTypeId not found with given ID: 10",
"status":404,
"timestamp":"2024-11-06T00:04:17.5243706",
"path":"uri=/api/eventTicketTypes/1"
}
```

**Condition: Price is zero or less**

Code: `400 Bad Request`

Error Example:

```json
{
   "message":"Price must be greater than zero",
   "status":400,
   "timestamp":"2024-11-06T00:24:05.6507762",
   "path":"uri=/api/eventTicketTypes/1"
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
