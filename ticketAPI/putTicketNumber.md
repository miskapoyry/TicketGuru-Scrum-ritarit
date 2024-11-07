## Update a ticket

**Method** : `PUT`

**URL** : `/api/tickets/{ticketNumber}/use?used`

 **Success Response** :

- Status Code: `200 OK`

**Request Body** :

Updates the ticket as used (by default) or as not used based on the used query parameter. If the used parameter is not specified, the ticket will be marked as used (true).
If the used parameter is specified as false, the ticket will be marked as not used.

In case of an error, you can mark the ticket as unused with this:

**URL** : `/api/tickets/{ticketNumber}/use?used=false`

**Error Responses** :

**Condition: If the ticket does not exist**

Code: ```404 Not Found``` 

Error Example:

```json
{
  "message": "Ticket not found",
  "status": 404,
  "timestamp": "2024-11-07T20:46:38.314145",
  "path": "uri=/api/tickets/TICKET-1005/use"
}
```
