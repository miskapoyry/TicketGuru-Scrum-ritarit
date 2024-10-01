# TicketGuru API Documentation

## Event Resource

The Event Resource allows you to manage events in the TicketGuru application. It supports creating, updating,
retrieving, and deleting events.

## Endpoints

### Create event
- Method: POST

- Request Body: { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer" }

Query Parameters:

- userId: Long (ID of the user creating the event)

Response:

- Status Code: 200 OK
- Body: { "eventId": "Long", "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long", "username": "string" } }
- Error Responses:

- 400 Bad Request: If the event already has an ID or the user is not found. { "error": "User not found" }
- Update Event
- URL: /api/event/{id}

### Update event
- Method: PUT
- Path Parameters:

- id: Long (ID of the event to update)
Request Body: { "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long" } }

Response:

- Status Code: 200 OK
- Body: { "eventId": "Long", "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
availableTickets": "integer", "appUser": { "id": "Long", "username": "string" } }
- Error Responses:

- 404 Not Found: If the event does not exist.
- Get Event
- URL: /api/event/{id}

### Get events
- Method: GET

Path Parameters:
- id: Long (ID of the event to retrieve)

Response:

- Status Code: 200 OK
- - Body: [ { "eventId": "Long", "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "availableTickets": "integer", "appUser": { "id": "Long", "username": "string" } }, ... ]
- Error Responses:

- 404 Not Found: If the event does not exist.
- Get All Events
- URL: /api/event

### Get event
- Method: GET

Response:

- Status Code: 200 OK
- Body: { "eventId": "Long", "eventName": "string", "eventDate": "string (ISO 8601 format)", "location": "string", "
  availableTickets": "integer", "appUser": { "id": "Long", "username": "string" } }

- 404 Not Found: If the event does not exist.
- Get Event
- URL: /api/event/{id}

### Delete event
- Method: DELETE

Path Parameters:

id: Long (ID of the event to delete)
Response:

- Status Code: 200 OK

- 404 Not Found: If the event does not exist.
- 
- Delete Event
- URL: /api/event/{id}
- 
#### Notes

Ensure that the eventDate follows the ISO 8601 format for proper date-time representation.
The userId parameter is mandatory for creating an event and must correspond to an existing user.
