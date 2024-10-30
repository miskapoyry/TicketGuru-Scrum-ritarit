### Get  all sales

**Method** : `GET`

**URL** : `/api/sales`

**Query Parameters**

- `userId` : Filters sales by creator's userId (has to be exact)

**Success Response** :

- Status Code: `200 OK`

```json
[
   {
        "saleId": 1,
        "paymentMethod": "Credit Card",
        "totalPrice": 199.98,
        "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
        "userId": 1,
        "ticketSummaries": [
            {
                "ticketId": 1,
                "quantity": 1,
                "price": 99.99,
                "eventId": 1
            },
            {
                "ticketId": 2,
                "quantity": 1,
                "price": 99.99,
                "eventId": 1
            }
        ],
        "message": null
    },
    {
        "saleId": 2,
        "paymentMethod": "PayPal",
        "totalPrice": 49.99,
        "saleTimestamp": "2024-03-05T12:30:00.000+00:00",
        "userId": 2,
        "ticketSummaries": [
            {
                "ticketId": 3,
                "quantity": 1,
                "price": 149.99,
                "eventId": 2
            }
        ],
        "message": null
    } 
]
```

**Error Responses** :

Condition: If the userId does not exist

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "User not found with given ID"
}
```