## Show one ticket

**Method** : `GET`

**URL** : `/api/tickets/{ticketId}`

**Description** : Retrieves a specific ticket based on the provided ticketId. The response includes detailed information about the ticket, including the event, ticket type, sale details, and whether the ticket has been used.

**Success Response** :

- Status Code: `200 OK`

**Error Responses** :

- 404 Not Found: This occurs if no ticket is found with the given ticketId.

- 400 Bad Request: This occurs if the provided ticketId is invalid, such as a non-numeric value.

- 500 Internal Server Error: This occurs if there is a server-side error while retrieving the ticket, such as a database issue.

**Response Body** : 

A ticket objects containing the following fields:

```json
{
  "ticketId": 2,
  "ticketNumber": "TICKET-1002",
  "eventId": 1,
  "ticketTypeId": 1,
  "saleId": 1,
  "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
  "usedTimestamp": null,
  "used": false
}

```

### Field Descriptions
- **`ticketId`**: Unique identifier for the ticket.
- **`ticketNumber`**: The alphanumeric identifier assigned to the ticket.
- **`eventId`**: Identifier for the associated event.
- **`ticketTypeId`**: Identifier for the type of ticket (e.g., general admission, VIP).
- **`saleId`**: Identifier for the sale transaction related to this ticket.
- **`saleTimestamp`**: Date and time when the ticket was purchased, in ISO 8601 format.
- **`usedTimestamp`**: Date and time when the ticket was used (if applicable), in ISO 8601 format. This field will be `null` if the ticket has not been used.
- **`used`**: Boolean indicating whether the ticket has been used (`true`) or not (`false`).