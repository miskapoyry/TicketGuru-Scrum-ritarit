### Delete event

**Method** : `DELETE`

**URL** : `/api/role/{id}`

**Path Parameters** : 

- id: Long (ID of the role to delete)

**Succesfull Response** :

- Status Code: `204 No Content`

**Error Responses** :

**Condition: If the role does not exist.**

Code: `404 Not Found`

Error Example:

```json
{
    "message": "Role not found with given ID"
}
```

