### Update rooli

**Method** : `PUT`

**URL** : `/api/event/{id}`

**Path Parameters** :

- id: Long (ID of the role to update)

**Request Body** : 

{
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

**Condition: If role does not exist**

Code: `404 Not Found`

Error Example:

```json
{
    "message": "Role not found"
}
```