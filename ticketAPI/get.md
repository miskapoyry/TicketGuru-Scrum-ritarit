## Show all tickets

**Method** : `GET`

**URL** : `/api/tickets`

**Description** : Retrieves a list of all tickets. Each ticket includes information such as its unique identifier, event, sale details, and usage status. You can filter the results by using optional query parameters eventId and saleId.

**Query Parameters** : 
- eventId (optional): The identifier of the event to filter tickets. If provided, only tickets associated with the specified event will be returned.
- saleId (optional): The identifier of the sale to filter tickets. If provided, only tickets associated with the specified sale will be returned.

**Success Response** :

- Status Code: `200 OK`

**Response Body** : 

An array of ticket objects, where each object contains the following fields:

```json
[
  {
    "ticketId": 1,
    "ticketNumber": "TICKET-1001",
    "eventId": 1,
    "eventName": "Tech Conference 2024",
    "ticketTypeId": 1,
    "ticketTypeName": "Regular",
    "saleId": 1,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "usedTimestamp": null,
    "quantity": 1,
    "price": 99.99,
    "used": false
  },
  {
    "ticketId": 2,
    "ticketNumber": "TICKET-1002",
    "eventId": 1,
    "eventName": "Tech Conference 2024",
    "ticketTypeId": 1,
    "ticketTypeName": "Regular",
    "saleId": 1,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "usedTimestamp": null,
    "quantity": 1,
    "price": 99.99,
    "used": false
  },
  {
    "ticketId": 3,
    "ticketNumber": "TICKET-1003",
    "eventId": 2,
    "eventName": "Art Exhibition",
    "ticketTypeId": 2,
    "ticketTypeName": "VIP",
    "saleId": 2,
    "saleTimestamp": "2024-03-05T12:30:00.000+00:00",
    "usedTimestamp": null,
    "quantity": 1,
    "price": 149.99,
    "used": false
  }
]
```
### Field Descriptions
- **`ticketId`**: Unique identifier for the ticket.
- **`ticketNumber`**: The alphanumeric identifier assigned to the ticket.
- **`eventId`**: Identifier for the associated event.
- **`eventName`**: Name of the associated event.
- **`ticketTypeId`**: Identifier for the type of the ticket (e.g., general admission, VIP).
- **`ticketTypeName`**: Name of the type of the ticket (e.g., general admission, VIP).
- **`saleId`**: Identifier for the sale transaction related to this ticket.
- **`saleTimestamp`**: Date and time when the ticket was purchased, in ISO 8601 format.
- **`usedTimestamp`**: Date and time when the ticket was used (if applicable), in ISO 8601 format. This field will be `null` if the ticket has not been used.
- **`used`**: Boolean indicating whether the ticket has been used (`true`) or not (`false`).
- **`quantity`**: The number of tickets available.
- **`price`**: The price of the ticket.
