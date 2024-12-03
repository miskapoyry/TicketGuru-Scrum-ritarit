### Create event
**Method** : `PUT`

**URL** : `/api/event/{id}`

**Path Parameters** :

- id: Long (ID of the event to update)

**Success Response** :

- Status Code: `200 OK`

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

```json
{
    "userId": 1,
    "eventName": "Ooppera",
    "eventDate": "2024-12-25T18:00:00.000+00:00",
    "location": "Helsinki",
    "eventTicketTypes": [
        {
            "ticketTypeId": 1,
            "ticketQuantity": 1000,
            "price": 200
        }
    ]
}
```

**Content Example** : For the example above, when the eventName is updated.

```json
{
    "userId": 1,
    "eventName": "Ei ainakaan ooppera",
    "eventDate": "2024-12-25T18:00:00.000+00:00",
    "location": "Helsinki",
    "eventTicketTypes": [
        {
            "ticketTypeId": 1,
            "ticketQuantity": 1000,
            "price": 200
        }
    ]
}
```

**Error responses :**

**Condition: If the eventId does not exist**

Code: ```404 Not Found``` 

Error example:

```json
{
    "message": "Event not found with given ID",
    "status": 404,
    "timestamp": "2024-10-29T21:42:02.2683948",
    "path": "uri=/api/events/66"
}
```

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
