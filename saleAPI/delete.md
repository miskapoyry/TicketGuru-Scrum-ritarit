### Delete sale

**Method** : `DELETE`

**URL** : `/api/sales/{id}`

**Path Parameters** : 
 - id: Long (ID of the sale to delete)

 **Success Response** :

- Status Code: `204 No content`

**Error Responses** :

Condition: If the saleID does not exist

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "Sale not found with given ID",
    "status": 404,
    "timestamp": "2024-10-20T20:55:45.1877334",
    "path": "uri=/api/sales/4"
}
```