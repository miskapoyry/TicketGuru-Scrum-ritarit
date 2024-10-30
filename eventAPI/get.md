### Get  all events

**Method** : `GET`

**URL** : `/api/events`

**Succes Response** :

- Status Code: `200 OK`

**Query Parameters** :

- `eventName` : Filters events by name (case-insensitive, partial matches allowed)

**Response body** :

```json
{
    "eventId": 1,
    "userId": 1,
    "eventName": "Tech Conference 2024",
    "eventDate": "2024-04-15T06:00:00.000+00:00",
    "location": "San Francisco, CA",
    "totalTickets": 500,
    "availableTickets": 250,
    "ticketTypes": {
        "Regular": 99.99,
        "VIP": 149.99
    }
}
```
