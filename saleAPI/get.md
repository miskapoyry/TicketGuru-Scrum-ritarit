### Get  all sales

**Method** : `GET`

**URL** : `/api/sales`

**Succes Response** :

- Status Code: `200 OK`

**Request Body** : 

- { "paymentMethod": "string", "saletimestamp": "string (ISO 8601 format)", "totalPrice": "bigdecimal", "
"ticketIds": ["Long"], "appUser": { "id": "Long" } }

```json
[
    {
        "saleId": 1,
        "paymentMethod": "Credit Card",
        "totalPrice": 199.98,
        "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
        "userId": 1,
        "ticketIds": []
    },
    {
        "saleId": 2,
        "paymentMethod": "PayPal",
        "totalPrice": 49.99,
        "saleTimestamp": "2024-03-05T12:30:00.000+00:00",
        "userId": 2,
        "ticketIds": []
    },
    {
        "saleId": 3,
        "paymentMethod": "Cash",
        "totalPrice": 14.98,
        "saleTimestamp": "2024-03-01T08:00:00.000+00:00",
        "userId": 1,
        "ticketIds": []
    },
    {
        "saleId": 4,
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