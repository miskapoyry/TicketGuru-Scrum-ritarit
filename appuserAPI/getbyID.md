### Get usersbyID

**Method** : `GET`

**URL** : `/api/users/{id}`

**Path Parameters** : 
- id: Long (ID of the user to retrieve)

**Succes Response** :

- Status Code: `200 OK`

**Response Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }

```json
{
    "userId": 2,
    "username": "jane_smith",
    "passwordHash": "admin123",
    "roleId": 2,
    "eventIds": [2],
    "saleIds": [2]
}
```

**Error Responses** :

Condition: If the user does not exist

Code: ```404 Not Found``` 

Response Example:

```json
{
    "timestamp": "2024-10-20T12:06:13.209+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "User with ID {id} not found",
    "path": "/api/users/{id}"
}
```