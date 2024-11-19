### Create role
**Method** : `POST`

**URL** : `/api/role`

**Request Body** :

- {
    "roleName": "string"
}

**Successfull Response** :

- Status Code: `200 OK`

**Response Body** :

{
    "roleId": "Long",
    "roleName": "string"
}

```json
{
    "roleId": 1,
    "roleName": "ADMIN"
}
```

**Error Responses** :

**Condition: If the role already has an ID**

Code: `400 Bad Request`

Error Example:

```json
{
    "error": "Bad Request",
    "message": "Role ID should be null for new role."
}
```