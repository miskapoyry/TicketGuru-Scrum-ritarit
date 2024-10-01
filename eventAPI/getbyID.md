### Get eventbyID

**Method** : `GET`

**URL** : `/api/event/{id}`

**Path Parameters** : 
- id: Long (ID of the event to retrieve)

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
    }
]
```

**Error Responses** :

Condition: If the event does not exist

Code: ```404 Not Found``` 

Content: ```{}```