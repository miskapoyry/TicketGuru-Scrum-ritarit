### Get Event Report by eventID

**Method** : `GET`

**URL** : `/api/events/{id}/report`

**Path Parameters** : 
- id: Long (ID of the event to retrieve)

**Success Response** :

- Status Code: `200 OK`

### Success Response
**Code**: 200 OK

**Content example**:
```json
[
   {
      "eventName":"Tech Conference 2024",
      "ticketTypeName":"Regular",
      "ticketsSold":20,
      "totalRevenue":1999.8
   },
   {
      "eventName":"Tech Conference 2024",
      "ticketTypeName":"VIP",
      "ticketsSold":0,
      "totalRevenue":0.0
   }
]
```