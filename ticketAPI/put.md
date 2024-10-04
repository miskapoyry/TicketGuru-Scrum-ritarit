## Update a ticket

**Method** : `PUT`

**URL** : `/api/tickets/{ticketId}`

 **Success Response** :

- Status Code: `200 OK`

**Error Responses** :

- 404 Not Found: This occurs if the ticket with the specified ticketId does not exist, or if any associated entities (eventId, ticketTypeId, saleId) are not found.

- 400 Bad Request: This occurs if any of the required fields are missing, invalid, or refer to non-existent entities (e.g., eventId, ticketTypeId, saleId).

- 409 Conflict: This occurs if the ticketNumber provided in the update request conflicts with an existing ticket in the system.

- 500 Internal Server Error: This occurs if there is a server-side error while retrieving the ticket, such as a database issue.

**Request Body** :

Updates the details of an existing ticket specified by the ticketId.

```json
{
  "ticketNumber": "TICKET-101202",
  "eventId": 1,
  "ticketTypeId": 2,
  "saleId": 1,
  "saleTimestamp": "2024-01-01T08:00:00.000+00:00",
  "usedTimestamp": null,
  "used": false
}

```

### Field Descriptions
- **`ticketNumber`**: The alphanumeric identifier assigned to the ticket.
- **`eventId`**: Identifier for the associated event.
- **`ticketTypeId`**: Identifier for the type of ticket (e.g., general admission, VIP).
- **`saleId`**: Identifier for the sale transaction related to this ticket.
- **`saleTimestamp`**: Date and time when the ticket was purchased, in ISO 8601 format.
- **`usedTimestamp`**: Date and time when the ticket was used (if applicable), in ISO 8601 format. This field will be `null` if the ticket has not been used.
- **`used`**: Boolean indicating whether the ticket has been used (`true`) or not (`false`).
