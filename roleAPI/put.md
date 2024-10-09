### Update rooli

**Method** : `PUT`

**URL** : `/api/event/{id}`

**Path Parameters** :

- id: Long (ID of the role to update)

**Request Body** : 

{
    "roleName": "string"
}

**Success Response** :

- Status Code: `200 OK`

Response:

{
    "roleId": "Long",
    "roleName": "string"
}

Virhevastaukset:

404 Not Found: If role not found.