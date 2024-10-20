### Update a Ticket Type

**Method**: `PUT`

**URL**: `/api/ticket-types/{id}`

**Request Body**: 

- `{ "ticketTypeName": "string" }`

**Path Parameters**:

- `id`: Long (ID of the ticket type to update)

**Success Response**:

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

Condition: If the ticket type does not exist

Code: ```404 Not Found``` 

Response Example:

```json
{
    "message": "Ticket type with ID {id} not found"
}
```

Condition: If the ticket type name is missing or invalid

Code: ```400 Bad Request```

Error Message: "Ticket type name is required"

Condition: If a ticket type with the same name already exists

Code: ```400 Bad Request```

Response Example:

```json
{
  "message": "Ticket type with the same name already exists"
}
```
