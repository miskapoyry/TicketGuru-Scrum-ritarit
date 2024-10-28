### Create event
**Method** : `POST`

**URL** : `/api/events`

**Request Body** : {
  "eventName": "string",
  "eventDate": "string (ISO 8601 format)",
  "location": "string",
  "totalTickets": "integer",
  "availableTickets": "integer",
  "userId": "integer",
  "ticketTypes": {
    "Student": "decimal",
    "Child": "decimal"
  }
}

**Success Response** :

- Status Code: 200 OK

**Body** : 

- {
    "eventId": "integer",
    "userId": "integer",
    "eventName": "string",
    "eventDate": "string (ISO 8601 format)",
    "location": "string",
    "totalTickets": "integer",
    "availableTickets": "integer",
    "ticketTypes": {
        "Student": "decimal",
        "Child": "decimal"
    }
}

```json
{
    "eventId": 7,
    "userId": 1,
    "eventName": "Party",
    "eventDate": "2024-11-25T18:00:00.000+00:00",
    "location": "Helsinki",
    "totalTickets": 1000,
    "availableTickets": 1000,
    "ticketTypes": {
        "Student": 100.0,
        "Child": 50.0
    }
}
```

**Error Responses** :

**Condition: If the userId isn't valid**

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "User not found with given ID",
    "status": 404,
    "timestamp": "2024-10-28T16:56:18.4098435",
    "path": "uri=/api/events"
}
```

**Condition: If the userId is null**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "User ID cannot be null",
    "status": 400,
    "timestamp": "2024-10-28T16:57:02.5596549",
    "path": "uri=/api/events"
}
```

**Condition: If the event date is in the past**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Event date must be in the future",
    "status": 400,
    "timestamp": "2024-10-28T16:58:19.0950561",
    "path": "uri=/api/events"
}
```

**Condition: Location is empty**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Location cannot be empty",
    "status": 400,
    "timestamp": "2024-10-28T16:58:53.2901244",
    "path": "uri=/api/events"
}
```

**Condition: Total tickets is 0 or empty**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Total tickets must be at least 1",
    "status": 400,
    "timestamp": "2024-10-28T17:00:50.1643958",
    "path": "uri=/api/events"
}
```

**Condition: Available tickets is greater than total tickets**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Available tickets cannot be greater than total tickets",
    "status": 400,
    "timestamp": "2024-10-28T17:01:32.0485452",
    "path": "uri=/api/events"
}
```

**Condition: Event doesn't contain any ticket types**

Code: ```400 Bad Request``` 

Error example:
```json
{
    "message": "Event must contain at least one ticket type",
    "status": 400,
    "timestamp": "2024-10-28T17:03:52.0748676",
    "path": "uri=/api/events"
}
```