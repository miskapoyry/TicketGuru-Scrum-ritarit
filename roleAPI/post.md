### Create role
**Method** : `POST`

**URL** : `/api/role`

**Request Body** :

{
    "roleName": "string"
}

**Success Response** :

- Status Code: 200 OK

Answer:

{
    "roleId": "Long",
    "roleName": "string"
}

**Error Responses** :

400 Bad Request: If the role already has an ID.

{
    "error": "Bad Request",
    "message": "Role ID should be null for new role."
}