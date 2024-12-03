### Get a Sales Report of an Event

**Method** : `GET`

**URL** : `/api/sales/summary/{id}`

**Path Parameters** : 
- id: Long (ID of the event to retrieve)

**Success Response** :

- Status Code: `200 OK`

### Success Response
**Code**: 200 OK

**Content example**:
```json

{
   "eventName":"Tech Conference 2024",
   "totalSales":6,
   "totalRevenue":1999.8,
   "salesByWeek":{
      "2024-W49":5,
      "2024-W9":1
   },
   "revenueByWeek":{
      "2024-W49":1799.82,
      "2024-W9":199.98
   },
   "salesByUserAndTicketType":{
      "1":{
         "Regular":{
            "ticketsSold":20,
            "revenue":1999.8
         }
      }
   },
   "salesByTicketType":{
      "Regular":20
   },
   "revenueByTicketType":{
      "Regular":1999.8
   }
}
```