## Update a ticket

**Method** : `PUT`

**URL** : `/api/tickets/{ticketId}/use?used`

 **Success Response** :

- Status Code: `200 OK`

**Request Body** :

Updates the ticket as used (by default) or as not used based on the used query parameter. If the used parameter is not specified, the ticket will be marked as used (true).
If the used parameter is specified as false, the ticket will be marked as not used.

**Error Responses** :

**Condition: If the ticket does not exist**

Code: ```404 Not Found``` 

Error Example:

```json
{
  "message": "Ticket not found",
  "status": 404,
  "timestamp": "2024-10-21T21:29:20.1232713",
  "path": "uri=/api/tickets/344/use"
}
```
**Condition: This occurs if the provided ticketId is invalid, such as a non-numeric value.**

Code: `400 Bad Request`

Error Example:

```json
{
  "message": "Invalid parameter type: asdf for ticketId",
  "status": 400,
  "timestamp": "2024-11-03T15:07:16.3086218",
  "path": "uri=/api/tickets/asdf/use"
}
```
