### Create a Ticket Type

**Method**: `POST`

**URL**: `/api/ticket-types`

**Request Body**:

- { "ticketTypeName": "string" }

**Successful Response**:

- **Status Code**: `201 Created`

**Response Body**:

```json
{
    "ticketTypeName": "Regular"
}

```

**Error Responses** :

Condition: If the ticket type name is missing or empty

Code: ```400 Bad Request```

Response Example:

```json
{
  "message": "Ticket type name is required",
  "status": 400,
  "timestamp": "2024-10-21T20:01:51.8271693",
  "path": "uri=/api/ticket-types"
}
```

Condition: If a ticket type with the same name already exists

Code: ```400 Bad Request```

Response Example:

```json
{
  "message": "Ticket type with the same name already exists",
  "status": 400,
  "timestamp": "2024-10-21T20:03:16.6964628",
  "path": "uri=/api/ticket-types"
}
```
