## Update Ticket

**Method** : `PUT`

**URL** : `/api/tickets/{ticketId}`

 **Success Response** :

- Status Code: `200 OK`

**Request Body** :

```json
{
  "ticketNumber": "TICKET-101202",
  "eventId": 1,
  "ticketTypeId": 2,
  "saleId": 1,
  "saleTimestamp": "2024-01-01T08:00:00.000+00:00",
  "usedTimestamp": null,
  "used": false
}
