### Create event
**Method** : `POST`

**URL** : `/api/event?userId={Id}`

**Request Body** : { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer" }

**Query Parameters** :

- Id : Long (userID of the user creating the event)

- The userId parameter is mandatory for creating an event and must correspond to an existing user.

**Succes Response** :

- Status Code: 200 OK

**Body** : 

- { "eventId": "Long", "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long", "username": "string" } }

```json
[
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

**Error Responses** :

- 500 Internal Server Error: If the event already has an ID or the user is not found. 
```
 "error": "Internal Server Error" , "message": " User not found"
```
