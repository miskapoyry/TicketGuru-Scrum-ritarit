### Get rolebyID

**Method** : `GET`

**URL** : `/api/role/{id}`

**Path Parameters** : 

- id: Long (ID of the role to retrieve)

**Success Response** :

- Status Code: `200 OK`

Response:

{
    "roleId": "Long",
    "roleName": "string"
}

**Error Responses** :

404 Not Found: If role not found.