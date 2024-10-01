### Get  all events

**Method** : `GET`

**URL** : `/api/event`

**Succes Response** :

- Status Code: `200 OK`

**Request Body** : 

- { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long" } }

```json
[
    {
        "eventId": 1,
        "eventName": "Updated Festival",
        "eventDate": "2025-04-15T06:00:00.000+00:00",
        "location": "San Francisco, CA",
        "totalTickets": 500,
        "availableTickets": 250
    },
    {
        "eventId": 2,
        "eventName": "Art Exhibition",
        "eventDate": "2024-05-10T07:00:00.000+00:00",
        "location": "New York, NY",
        "totalTickets": 300,
        "availableTickets": 150
    },
    {
        "eventId": 3,
        "eventName": "Music Festival",
        "eventDate": "2024-06-20T11:00:00.000+00:00",
        "location": "Los Angeles, CA",
        "totalTickets": 1000,
        "availableTickets": 500
    },
    {
        "eventId": 6,
        "eventName": "Sample Event",
        "eventDate": "2024-09-30T12:00:00.000+00:00",
        "location": "Sample Location",
        "totalTickets": 0,
        "availableTickets": 100
    },
    {
        "eventId": 8,
        "eventName": "Testi",
        "eventDate": "2025-04-15T06:00:00.000+00:00",
        "location": "San Francisco, CA",
        "totalTickets": 1000,
        "availableTickets": 250
    }
]
```










