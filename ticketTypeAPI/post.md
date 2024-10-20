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

Response Message: "Ticket type name is required"

Condition: If a ticket type with the same name already exists

Code: ```400 Bad Request```

Response Example:

```json
{
  "message": "Ticket type with the same name already exists"
}
```
