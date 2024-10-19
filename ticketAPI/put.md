## Update a ticket

**Method** : `PUT`

**URL** : `/api/tickets/{ticketId}/use?used`

 **Success Response** :

- Status Code: `200 OK`

**Error Responses** :

- 404 Not Found: This occurs if the ticket with the specified ticketId does not exist.

- 400 Bad Request: This occurs if the ticketId is invalid.

**Request Body** :

Updates the ticket as used (by default) or as not used based on the used query parameter. If the used parameter is not specified, the ticket will be marked as used (true).