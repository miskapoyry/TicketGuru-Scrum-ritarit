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

- 500 Internal Server Error: If the username is already in use
```
 "error": "Internal Server Error" , "message": "could not execute statement [(conn=516) Duplicate entry '' for key 'app_user.username'] [insert into app_user (passwordhash,role_id,username) values (?,?,?)]; SQL [insert into app_user (passwordhash,role_id,username) values (?,?,?)]; constraint [app_user.username]",
```
