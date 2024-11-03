### Create event
**Method** : `PUT`

**URL** : `/api/users/{id}`

**Request Body** : 

- { 
    "username": "string", 
    "passwordHash": "string", 
    "roleId": "integer", 
    "eventIds": ["integer"], 
    "saleIds": ["integer"] 
    }

**Path Parameters** :

- id: Long (ID of the user to update)

**Succes Response** :

- Status Code: `200 OK`

**Response Body** : 

- { 
    "username": "string", 
    "passwordHash": "string", 
    "roleId": "integer", 
    "eventIds": ["integer"], 
    "saleIds": ["integer"] 
    }


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

Content Example: For the example above, when the username and passwordHash is updated.

**Error Responses** :

**Condition: If the user does not exist**

Code: ```404 Not Found``` 

Error Example:

```json
{
    "message": "User with ID {id} not found",
    "status": 404,
    "timestamp": "2024-10-24T16:19:33.6518351",
    "path": "uri=/api/users/{id}"
}
```

**Condition: If the updated username already exists**

Code: ```400 Bad Request```

Error Example:

```json
{
    "message": "Username already exists: john_doe",
    "status": 400,
    "timestamp": "2024-10-24T16:21:09.4803117",
    "path": "uri=/api/users/19"
}
```

Content Example: User "john_doe" already exists in the database.