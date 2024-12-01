## Show one payment method

**Method** : `GET`

**URL** : `/api/paymentmethods/{id}`

**Description** : Retrieves a specific ticket based on the provided id. The response includes the id and name of the payment method.

**Successful Response** :

- Status Code: `200 OK`

**Response Body** : 

An json object containing the following fields:

```json
{
  "id": 1,
  "name": "Credit Card"
}
```

### Field Descriptions
- **`id`**: Unique identifier for the payment method.
- **`name`**: The alphanumeric identifier assigned to the payment method.

**Error Responses** :

**Condition: This occurs if no payment method is found with the given id**

Code: `404 Not Found`

Error Example:

```json
{
  "message": "Payment method with ID 13 not found",
  "status": 404,
  "timestamp": "2024-11-27T16:17:02.4082044",
  "path": "uri=/api/paymentmethods/13"
}
```

**Condition: This occurs if the provided id is invalid, such as a non-numeric value.**

Code: `400 Bad Request`

Error Example:

```json
{
  "message": "Invalid parameter type: adda for id",
  "status": 400,
  "timestamp": "2024-11-27T16:17:50.3820255",
  "path": "uri=/api/paymentmethods/adda"
}
```