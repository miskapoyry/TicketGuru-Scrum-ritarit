### Delete a Ticket Type

**Method**: `DELETE`

**URL**: `/api/ticket-types/{id}`

**Path Parameters**:
- `id`: Long (ID of the ticket type to delete)

**Successful Response**:

- **Status Code**: `204 No Content`

**Error Responses**:

**Condition**: If the ticket type does not exist

- **Code**: `404 Not Found`

**Response Example**:

```json
{
    "message": "TicketType with ID {id} not found"
}
