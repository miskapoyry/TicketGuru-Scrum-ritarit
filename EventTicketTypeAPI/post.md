### Create a eventTicketType

**Method**: `POST`

**URL**: `/api/eventTicketTypes`

**Request Body**:

```json
    {
        "eventId": 1,
        "ticketTypeId": 2,
        "ticketQuantity": 100,
        "price": 50.00
    }
```

**Successful Response**:

- **Status Code**: `201 Created`

**Response Body**:

```json
    {
        "eventTicketTypeId": 7,
        "eventId": 1,
        "ticketTypeId": 2,
        "ticketQuantity": 100,
        "price": 50.0,
        "eventName": "Tech Conference 2024",
        "ticketTypeName": "VIP"
    }
```

