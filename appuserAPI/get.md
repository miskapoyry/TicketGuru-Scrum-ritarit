### Get  all users

**Method** : `GET`

**URL** : `/api/users`

**Succes Response** :

- Status Code: `200 OK`

**Query Parameters**

- `username` : Filters users by name (case-insensitive, partial matches allowed)

**Response Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }

```json
[
    {
        "userId": 1,
        "username": "john_doe",
        "passwordHash": "password123",
        "roleId": 1,
        "eventIds": [1],
        "saleIds": [1]
    },
    {
        "userId": 2,
        "username": "jane_smith",
        "passwordHash": "admin12345",
        "roleId": 2,
        "eventIds": [2],
        "saleIds": [2]
    },
    {
        "userId": 3,
        "username": "alice_jones",
        "passwordHash": "securepass",
        "roleId": 1,
        "eventIds": [3],
        "saleIds": []
    },
    {
        "userId": 12,
        "username": "updateduser123",
        "passwordHash": "upadtedPasswordHash",
        "roleId": 2,
        "eventIds": [],
        "saleIds": []
    }
]
```










