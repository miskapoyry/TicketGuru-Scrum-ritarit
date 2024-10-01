## Get  all tickets

**Method** : `GET`

**URL** : `/api/tickets`

**Success Response** :

- Status Code: `200 OK`

**Response Body** : 

```json
[
  {
    "ticketId": 1,
    "ticketNumber": "TICKET-1001",
    "eventId": 1,
    "ticketTypeId": 1,
    "saleId": 1,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "usedTimestamp": null,
    "used": false
  },
  {
    "ticketId": 2,
    "ticketNumber": "TICKET-1002",
    "eventId": 1,
    "ticketTypeId": 1,
    "saleId": 1,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "usedTimestamp": null,
    "used": false
  },
  {
    "ticketId": 3,
    "ticketNumber": "TICKET-1003",
    "eventId": 2,
    "ticketTypeId": 2,
    "saleId": 2,
    "saleTimestamp": "2024-03-05T12:30:00.000+00:00",
    "usedTimestamp": "2024-05-10T12:00:00.000+00:00",
    "used": true
  }
]
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