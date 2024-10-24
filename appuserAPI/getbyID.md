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

**Response Example:**

```json
{
    "message": "User with ID {id} not found",
    "status": 404,
    "timestamp": "2024-10-24T16:11:32.1764181",
    "path": "uri=/api/users/{id}"
}
```