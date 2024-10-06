## Create a ticket

**Method** : `POST`

**URL** : `/api/tickets`

**Success Response** :

- Status Code: `201 Created`

**Error Responses** :

- 404 Not Found: This occurs if any of the associated entities (eventId, ticketTypeId, or saleId) do not exist in the database.

- 400 Bad Request: This occurs if any of the required fields are missing, invalid, or refer to non-existent entities (e.g., eventId, ticketTypeId, saleId).

- 409 Conflict: This occurs if a ticket with the same ticketNumber already exists.

- 500 Internal Server Error: This occurs if there is a server-side error while retrieving the ticket, such as a database issue.

**Request Body** :

A JSON object representing the created ticket, including its unique ticketId and all the details provided in the request.

```json
{
  "ticketNumber": "TICKET-10125",
  "eventId": 2,
  "ticketTypeId": 1,
  "saleId": 2,
  "saleTimestamp": "2024-01-02T08:00:00.000+00:00",
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