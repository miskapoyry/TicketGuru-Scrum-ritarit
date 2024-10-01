### Create event
**Method** : `PUT`

**URL** : `/api/event/{id}`

**Request Body** : { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer" }

**Path Parameters** :

- id: Long (ID of the event to update)

**Succes Response** :

- Status Code: `200 OK`

**Request Body** : 

- { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long" } }


```json
[
    {
    "eventId": 8,
    "eventName": "Updated name",
    "eventDate": "2025-04-15T06:00:00.000+00:00",
    "location": "San Francisco, CA",
    "totalTickets": 1000,
    "availableTickets": 250
    }
]
```

**Content Example** : For the example above, when the eventName is updated.

**Error Responses** :

Condition: If the event does not exist

Code: ```404 Not Found``` 

Content: ```{}```
