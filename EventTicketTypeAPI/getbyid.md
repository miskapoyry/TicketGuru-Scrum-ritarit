## Show all eventTicketType

**Method** : `GET`

**URL** : `/api/eventTicketTypes/{eventTicketTypeId}`

**Description** : 

- Retrieve a specific EventTicketTypes, based on the provided eventTicketTypeId.

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

**Error Responses** :

**Condition: This response occurs when there are no EventTicketTypes found in the system or the requested resource does not exist.**

Code: `404 Not Found`

Error Example:

```json
    {
        // Täydennä tähän
    }
```

**Condition: This response occurs if there is an issue with the request format or invalid parameters.**

Code: `400 Bad Request`

Error Example: 

```json
    {
        // Täydennä tähän
    }
```
