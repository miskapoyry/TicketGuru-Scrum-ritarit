### Update a Ticket Type

**Method**: `PUT`

**URL**: `/api/ticket-types/{id}`

**Request Body**: 

- `{ "ticketTypeName": "string" }`

**Path Parameters**:

- `id`: Long (ID of the ticket type to update)

**Successfull Response**:

- Status Code: `200 OK`

**Response Body**:

- `{ "ticketTypeId": "integer", "ticketTypeName": "string" }`

```json
{
    "ticketTypeId": 1,
    "ticketTypeName": "Updated Ticket Type Name"
}
```

**Error Responses** :

**Condition: If the ticket type does not exist**

Code: ```404 Not Found``` 

Error Example:

```json
{
  "message": "TicketType with ID 223 not found",
  "status": 404,
  "timestamp": "2024-10-21T20:04:44.015936",
  "path": "uri=/api/ticket-types/223"
}
```

**Condition: If the ticket type name is missing**

Code: ```400 Bad Request```

Error Example:

```json
{
  "message": "Ticket type name is required",
  "status": 400,
  "timestamp": "2024-10-21T20:05:20.0896098",
  "path": "uri=/api/ticket-types/223"
}
```

**Condition: If a ticket type with the same name already exists**

Code: `400 Bad Request`

Error Example:

```json
{
  "message": "Ticket type with the same name already exists",
  "status": 400,
  "timestamp": "2024-10-21T20:06:39.0051123",
  "path": "uri=/api/ticket-types/4"
}
```
