# TicketGuru API Documentation

## Event Resource

The Event Resource allows you to manage events in the TicketGuru application. It supports creating, updating,
retrieving, and deleting events.

* [Show all events](eventAPI/get.md) : `GET /api/event/`
* [Show one event](eventAPI/getbyID.md) : `Get /api/event/{id}`
* [Create An event](eventAPI/post.md) : `POST /api/event/api/event?userId={Id}`
* [Update An Event](eventAPI/put.md) : `PUT /api/event/{id}`
* [Delete An Account](eventAPI/delete.md) : `DELETE /api/event/{id}`

## Ticket Resource

The Ticket Resource allows you to manage tickets in the TicketGuru application. It supports updating,
retrieving, and deleting tickets.

* [Show all tickets](ticketAPI/get.md) : `GET /api/tickets`
* [Show one ticket](ticketAPI/getbyID.md) : `Get /api/tickets/{ticketId}`
* [Update a ticket](ticketAPI/put.md) : `PUT /api/tickets/ticketId}/use?used`

## Sale Resource

The Sale Resource allows you to manage sales in the TicketGuru application. It supports operations such as creating, updating, retrieving, and deleting sales.

* [Show All Sales](saleAPI/get.md) : `GET /api/sales`
* [Show One Sale](saleAPI/getbyID.md) : `Get /api/sales/{id}`
* [Create a Sale](saleAPI/post.md) : `POST /api/sales`
* [Update a Sale](saleAPI/put.md) : `PUT /api/sales/{id}`
* [Delete a Sale](saleAPI/delete.md) : `DELETE /api/sales/{id}`

## AppUser Resource

The AppUser Resource allows you to manage users in the TicketGuru application. It supports operations such as creating, updating, retrieving, and deleting user accounts.

* [Show All Users](appuserAPI/get.md) : `GET /api/users`
* [Show One User](appuserAPI/getbyID.md) : `Get /api/users/{id}`
* [Create a User](appuserAPI/post.md) : `POST /api/users`
* [Update a User](appuserAPI/put.md) : `PUT /api/users/{id}`
* [Delete a User](appuserAPI/delete.md) : `DELETE /api/users/{id}`

## Role Resource

The Role Resource allows you to manage roles in the TicketGuru application. It supports operations such as creating, updating, retrieving, and deleting roles.

* [Show All Roles](roleAPI/getall.md) : `GET /api/role`
* [Show One Role](roleAPI/getbyID.md) : `Get /api/role/{id}`
* [Create a Role](roleAPI/post.md) : `POST /api/role`
* [Update a Role](roleAPI/put.md) : `PUT /api/role/{id}`
* [Delete a Role](roleAPI/delete.md) : `DELETE /api/role/{id}`


## TicketType Resource

The TicketType Resource allows you to manage Ticket types in the TicketGuru application. It supports operations such as creating, updating, retrieving and deleting ticket types.

* [Show All Ticket types](ticketTypeAPI/get.md) : `GET /api/ticket-types`
* [Show One Ticket type](ticketTypeAPI/getbyID.md) : `Get /api/ticket-types/{id}`
* [Create a Ticket type](ticketTypeAPI/post.md) : `POST /api/ticket-types`
* [Update a Ticket type](ticketTypeAPI/put.md) : `PUT /api/ticket-types/{id}`
* [Delete a Ticket type](ticketTypeAPI/delete.md) : `DELETE /api/ticket-types/{id}`

## eventTicketType Resource

The eventTicketType Resource allows you to manage eventTicketTypes in the TicketGuru application. It supports operations such as creating, updating, retrieving and deleting eventTicketTypes.

* [Show All eventTicketTypes](EventTicketTypeAPI/get.md) : `/api/eventTicketTypes`
* [Show one eventTicketTypes](EventTicketTypeAPI/getbyid.md) : `/api/eventTicketTypes/{id}`
* [Create a eventTicketTypes](EventTicketTypeAPI/post.md) : `/api/eventTicketTypes`
* [Update a eventTicketTypes](EventTicketTypeAPI/put.md) : `/api/eventTicketTypes/{id}`
* [Delete a eventTicketTypes](EventTicketTypeAPI/delete.md) : `/api/eventTicketTypes/{id}`


## Endpoint Access Control

This table provides an overview of the authorization requirements for each endpoint in the TicketGuru API.

| Resource               | Endpoint                            | HTTP Method | Access Level/Role |
|------------------------|-------------------------------------|-------------|--------------------|
| **Event**              | `/api/event/`                      | `GET`       |    ADMIN, USER     |
|                        | `/api/event/{id}`                  | `GET`       |    ADMIN, USER     |
|                        | `/api/event?userId={Id}`           | `POST`      |    ADMIN           |
|                        | `/api/event/{id}`                  | `PUT`       |    ADMIN           |
|                        | `/api/event/{id}`                  | `DELETE`    |    ADMIN           |
| **Ticket**             | `/api/tickets`                     | `GET`       |    ADMIN, USER     |
|                        | `/api/tickets/{ticketId}`          | `GET`       |    ADMIN, USER     |
|                        | `/api/tickets/{ticketId}/use?used` | `PUT`       |    ADMIN, USER     |
| **AppUser**            | `/api/users`                       | `GET`       |    ADMIN           |
|                        | `/api/users/{id}`                  | `GET`       |    ADMIN           |
|                        | `/api/users`                       | `POST`      |    ADMIN           |
|                        | `/api/users/{id}`                  | `PUT`       |    ADMIN           |
|                        | `/api/users/{id}`                  | `DELETE`    |    ADMIN           |
| **Sale**               | `/api/sales`                       | `GET`       |    ADMIN, USER     |
|                        | `/api/sales/{id}`                  | `GET`       |    ADMIN, USER     |
|                        | `/api/sales`                       | `POST`      |    ADMIN, USER     |
|                        | `/api/sales/{id}`                  | `PUT`       |    ADMIN, USER     |
|                        | `/api/sales/{id}`                  | `DELETE`    |    ADMIN           |
| **TicketType**         | `/api/ticket-types`                | `GET`       |    ADMIN           |
|                        | `/api/ticket-types/{id}`           | `GET`       |    ADMIN           |
|                        | `/api/ticket-types`                | `POST`      |    ADMIN           |
|                        | `/api/ticket-types/{id}`           | `PUT`       |    ADMIN           |
|                        | `/api/ticket-types/{id}`           | `DELETE`    |    ADMIN           |
| **EventTicketType**    | `/api/eventTicketTypes`            | `GET`       |    ADMIN, USER     |
|                        | `/api/eventTicketTypes/{id}`       | `GET`       |    ADMIN, USER     |
|                        | `/api/eventTicketTypes`            | `POST`      |    ADMIN           |
|                        | `/api/eventTicketTypes/{id}`       | `PUT`       |    ADMIN           |
|                        | `/api/eventTicketTypes/{id}`       | `DELETE`    |    ADMIN           |

