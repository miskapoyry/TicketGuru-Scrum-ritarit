## Show all payment methods

**Method** : `GET`

**URL** : `/api/paymentmethods`

**Description** : Retrieves a list of all payment methods. Each item includes a unique identifier and a payment method name (e. g. "Cash", "Credit").

**Success Response** :

- Status Code: `200 OK`

**Response Body** : 

An array of payment methods, where each object contains the following fields:

```json
[
  {
    "id": 2,
    "name": "Cash"
  },
  {
    "id": 1,
    "name": "Credit Card"
  }
]

```
### Field Descriptions
- **`id`**: Unique identifier for the payment method.
- **`name`**: The alphanumeric identifier assigned to the payment method.
