### Get eventbyID

**Method** : `GET`

**URL** : `/api/events/{id}`

**Path Parameters** : 
- id: Long (ID of the event to retrieve)

**Success Response** :

- Status Code: `200 OK`

```json
{
    "eventId": 6,
    "userId": 1,
    "eventName": "Party",
    "eventDate": "2024-11-25T18:00:00.000+00:00",
    "location": "Himas",
    "totalTickets": 1000,
    "availableTickets": 1000,
    "ticketTypes": {
        "Student": 100.0,
        "Child": 50.0
    }
}
```

**Error Responses** :

Condition: If the eventId does not exist

Code: ```404 Not Found``` 

Error example:

```json
{
    "message": "Event with ID 66 not found",
    "status": 404,
    "timestamp": "2024-10-28T16:40:00.1241341",
    "path": "uri=/api/events/66"
}
```