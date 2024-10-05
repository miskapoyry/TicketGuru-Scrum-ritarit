### Delete user

**Method** : `DELETE`

**URL** : `/api/users/{id}`

**Path Parameters** : 
 - id: Long (ID of the user to delete)

 **Succes Response** :

- Status Code: `204 No Content`

**Error Responses** :

- 404 Not Found: If the user does not exist.