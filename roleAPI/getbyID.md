### Get rolebyID

**Method** : `GET`

**URL** : `/api/role/{id}`

**Path Parameters** : 

- id: Long (ID of the role to retrieve)

**Successfull Response** :

- Status Code: `200 OK`

**Response Body**:

```json
{
    "roleId": 1,
    "roleName": "ADMIN"
}
```

**Error Responses** :

**Condition: If role not found**

Code: `404 Not Found`

Error Example:

```json
    {
        "message": "Role not found"
    }
```