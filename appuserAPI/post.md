### Create user
**Method** : `POST`

**URL** : `/api/users`

**Request Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }

**Succes Response** :

- Status Code: 200 OK

**Response Body** : 

- { "username": "string", "passwordHash": "string", "roleId": "integer", "eventIds": ["integer"], 
"saleIds": ["integer"] }

```json
{
    "username": "new_user123",
    "passwordHash": "newPasswordHash",
    "roleId": 2,
    "eventIds": [],
    "saleIds": []
}
```

**Error Responses** :

Condition: If the username already exists

Code: ```400 Bad Request```

```json
{
    "timestamp": "2024-10-20T12:16:09.552+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Username already exists",
    "path": "/api/users"
}
```

Condition: Missing or invalid fields (e.g., password cannot be blank).

Code: ```400 Bad Request```

Response Example:

```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='appUserDTO'. Error count: 1",
    "errors": [
        {
            "field": "passwordHash",
            "rejectedValue": null,
            "message": "Password cannot be blank",
            "code": "NotBlank"
        }
    ],
    "path": "/api/users"
}
```
