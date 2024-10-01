## Get Ticket by ID

**Method** : `GET`

**URL** : `/api/tickets/{ticketId}`

**Success Response** :

- Status Code: `200 OK`

**Response Body** : 

```json
{
  "ticketId": 2,
  "ticketNumber": "TICKET-1002",
  "eventId": 1,
  "ticketTypeId": 1,
  "saleId": 1,
  "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
  "usedTimestamp": null,
  "used": false
}

