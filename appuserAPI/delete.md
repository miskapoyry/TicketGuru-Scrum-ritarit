### Delete user

**Method** : `DELETE`

**URL** : `/api/users/{id}`

**Path Parameters** : 
 - id: Long (ID of the user to delete)

 **Succes Response** :

- Status Code: `204 No Content`

**Error Responses** :

Condition: If the user does not exist

Code: ```404 Not Found``` 

Response Example:

```json
{
    "message": "User with ID {id} not found"
}
```