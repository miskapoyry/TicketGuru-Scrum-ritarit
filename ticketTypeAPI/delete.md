### Delete a Ticket Type

**Method**: `DELETE`

**URL**: `/api/ticket-types/{id}`

**Path Parameters**:
- `id`: Long (ID of the ticket type to delete)

**Successful Response**:

- Status Code: `204 No Content`

**Error Responses**:

**Condition**: If the ticket type does not exist

Code: `404 Not Found`

Error Example:

```json
{
  "message": "Ticket type with ID 512 not found",
  "status": 404,
  "timestamp": "2024-10-21T20:03:34.1248261",
  "path": "uri=/api/ticket-types/512"
}
