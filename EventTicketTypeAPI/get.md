## Show all eventTicketType

**Method** : `GET`

**URL** : `/api/eventTicketTypes`

**Description** : 

- Retrieve a list of all EventTicketTypes, and their information.

**Success Response** :

- Status Code: `200 OK`

**Response Body** : 

An array of EventTicketType objects, where each object contains the following fields:

```json
[
    {
        "eventTicketTypeId": 1,
        "eventId": 1,
        "ticketTypeId": 1,
        "ticketQuantity": 200,
        "price": 99.99,
        "eventName": "Tech Conference 2024",
        "ticketTypeName": "Regular"
    },
    {
        "eventTicketTypeId": 2,
        "eventId": 2,
        "ticketTypeId": 2,
        "ticketQuantity": 100,
        "price": 149.99,
        "eventName": "Art Exhibition",
        "ticketTypeName": "VIP"
    },
    {
        "eventTicketTypeId": 3,
        "eventId": 1,
        "ticketTypeId": 2,
        "ticketQuantity": 50,
        "price": 149.99,
        "eventName": "Tech Conference 2024",
        "ticketTypeName": "VIP"
    }
]
```
### Field Descriptions
- **`eventTicketTypeId`**: Unique identifier for the EventTicketType.
- **`eventId`**: Identifier for the associated event.
- **`ticketTypeId`**: Identifier for the type of ticket (e.g., general admission, VIP).
- **`ticketQuantity`**: How many tickets are available.
- **`price`**: Price per ticket.
- **`eventName`**: Name of event based off eventId.
- **`ticketTypeName`**: Name of tickettype based off ticketTypeId
