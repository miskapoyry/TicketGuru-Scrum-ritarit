### Create event
**Method** : `POST`

**URL** : `/api/events`

**Request Body** : 
{
  "userId": "integer",
  "eventName": "string",
  "eventDate": "string (ISO 8601 format)",
  "location": "string",
  "eventTicketTypes": [
        {
            "ticketTypeId": "integer",
            "ticketQuantity": "integer",
            "price": "decimal"
        }
    ]
}

**Success Response** :

- Status Code: 200 OK

```json
{
  "eventId": 5,
  "userId": 1,
  "eventName": "New Event",
  "eventDate": "2024-12-10T00:00:00.000+00:00",
  "location": "Helsinki",
  "totalTickets": 500,
  "availableTickets": 500,
  "eventTicketTypes": [
    {
      "eventTicketTypeId": 6,
      "eventId": 5,
      "ticketTypeId": 2,
      "ticketQuantity": 500,
      "price": 50.0,
      "eventName": "New Event",
      "ticketTypeName": "VIP"
    }
  ]
}

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

