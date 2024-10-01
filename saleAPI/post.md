### Create sale
**Method** : `POST`

**URL** : `/api/sales`

**Request Body** : - { "paymentMethod": "string", "saletimestamp": "string (ISO 8601 format)", "totalPrice": "bigdecimal", "
"ticketIds": ["Long"], "appUser": { "id": "Long" } }

**Query Parameters** :

- Id : Long (userID of the user creating the sale)

- The userId parameter is mandatory for creating an event and must correspond to an existing user.
- TicketIds : Long
- TicketIds are mandatory in order to know, which tickets are linked to certain sale.

**Succes Response** :

- Status Code: 200 OK

**Body** : 

- { "paymentMethod": "string", "saletimestamp": "string (ISO 8601 format)", "totalPrice": "bigdecimal", "
"ticketIds": ["Long"], "appUser": { "id": "Long" } }

```json
[
    {
    "saleId": 5,
    "paymentMethod": "Cash",
    "totalPrice": 14.98,
    "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
    "userId": 1,
    "ticketIds": [
        1,
        2,
        3
    ]
}
]
```

**Error Responses** :

- 500 Internal Server Error: If the user ID isn't found. 
```
 "error": "Internal Server Error" , "message": "User not found with given ID"
```

- 500 Internal Server Error: If a ticketId isn't found. 
```
 "error": "Internal Server Error" , "message": "Tickets not found with given ID"
```