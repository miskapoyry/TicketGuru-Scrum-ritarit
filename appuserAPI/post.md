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
    "message": "Username already exists: new_user123",
    "status": 400,
    "timestamp": "2024-10-24T16:13:19.8377492",
    "path": "uri=/api/users"
}
```

Condition: Missing or invalid fields (e.g., password cannot be blank).

Code: ```400 Bad Request```

**Response Examples:**

```json
{
    "message": "Username cannot be blank",
    "status": 400,
    "timestamp": "2024-10-24T16:14:19.9845202",
    "path": "uri=/api/users"
}
```
**Content Example** : For the example above, when the username is not given.

```json
{
    "message": "Password cannot be blank",
    "status": 400,
    "timestamp": "2024-10-24T16:15:02.7530365",
    "path": "uri=/api/users"
}
```
**Content Example** : For the example above, when the password is not given.

```json
{
    "message": "Role ID cannot be null",
    "status": 400,
    "timestamp": "2024-10-24T16:16:14.2142421",
    "path": "uri=/api/users"
}
```
**Content Example** : For the example above, when the role is not given.