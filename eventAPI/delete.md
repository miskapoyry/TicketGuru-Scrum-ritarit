### Delete event

**Method** : `DELETE`

**URL** : `/api/events/{id}`

**Path Parameters** : 
 - id: Long (ID of the event to delete)

 **Succes Response** :

- Status Code: `204 No Content`

**Error Responses** :

**Condition: If the eventId isn't valid**

Code: ```404 Not Found``` 

Error example:
```json
{
    "message": "Event not found",
    "status": 404,
    "timestamp": "2024-10-28T17:40:38.5703955",
    "path": "uri=/api/events/77"
}
```