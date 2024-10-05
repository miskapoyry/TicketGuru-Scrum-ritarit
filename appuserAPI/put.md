### Create event
**Method** : `PUT`

**URL** : `/api/users/{id}`

**Request Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }

**Path Parameters** :

- id: Long (ID of the user to update)

**Succes Response** :

- Status Code: `200 OK`

**Response Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }


```json
[
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

**Content Example** : For the example above, when the username and passwordHash is updated.

**Error Responses** :

Condition: If the user does not exist

Code: ```404 Not Found``` 

Content: ```{}```
