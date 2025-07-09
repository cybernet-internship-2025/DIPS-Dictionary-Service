# Digital Invoice Processing Dictionary Service

This lightweight REST service manages lookup ("dictionary") data for the Digital Invoice Processing system. You can retrieve, create/update, soft-delete, and restore dictionary entries via simple HTTP endpoints.

## Table of Contents

* [Features](#features)
* [Endpoints](#endpoints)

  * [GET /api/v1/dictionaries](#get-apiv1dictionaries)
  * [PUT /api/v1/dictionaries](#put-apiv1dictionariesid)
  * [DELETE /api/v1/dictionaries/{id}](#delete-apiv1dictionariesid)
  * [POST /api/v1/dictionaries/{id}/restore](#post-apiv1dictionariesidrestore)
* [Request & Response Examples](#request--response-examples)
* [Validation & Error Handling](#validation--error-handling)
* [Architecture](#architecture)
* [Data Model](#data-model)
* [Getting Started](#getting-started)
* [Testing](#testing)

---

## Features

* Retrieve all or filtered dictionary entries
* Create new entries or update existing ones
* Soft-delete entries (mark inactive)
* Restore soft-deleted entries
* Clear separation of Controller → Service → Repository layers

---

## Endpoints

### GET /api/v1/dictionaries

Retrieve all active dictionary entries by default.

**Query Parameters** (all optional):

* `id` (UUID or sequential)
* `value` (partial match, case-insensitive)
* `isActive` (boolean)
* `limit` (integer, max number of results)

**Response**

* **200 OK**: JSON array of dictionary entry objects.

### PUT /api/v1/dictionaries

Create a new entry or update an existing one.

**Request Body** (JSON):

* `id` (UUID or sequential, optional): If provided and exists (even soft-deleted), update that entry; if omitted or unknown, create a new one.
* `value` (string, **required**, non-empty)
* `description` (string, optional)


### DELETE /api/v1/dictionaries/{id}### DELETE /api/v1/dictionaries/{id}

Soft-delete an entry by marking it inactive and recording the deletion timestamp.

**Path Parameter**:

* `id`: Identifier of the entry to delete

### POST /api/v1/dictionaries/{id}/restore

Restore a soft-deleted entry by clearing its inactive flag and deletion timestamp.

**Path Parameter**:

* `id`: Identifier of the entry to restore

---

## Request & Response Examples

```http
# Retrieve all entries
GET /api/v1/dictionaries HTTP/1.1
Accept: application/json

# Response
HTTP/1.1 200 OK
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "value": "INVOICE_STATUS",
    "description": "Status codes for invoices",
    "isActive": true
  }
]
```

```http
# Create or update an entry
PUT /api/v1/dictionaries HTTP/1.1
Content-Type: application/json

{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "value": "PAYMENT_METHOD",
  "description": "Accepted payment methods"
}

# Response
HTTP/1.1 200 OK
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "value": "PAYMENT_METHOD",
  "description": "Accepted payment methods",
  "isActive": true
}
```

---

## Validation & Error Handling

* **400 Bad Request** for missing or empty `value` in PUT
* **404 Not Found** for GET/DELETE/RESTORE with unknown `id`
* **400 Bad Request** for RESTORE on an already active entry

Error responses return a JSON object:

```json
{
  "timestamp": "2025-07-09T13:45:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Value must not be empty"
}
```

---

## Architecture

* **Controller Layer**: Handles HTTP requests, maps to service methods
* **Service Layer**: Business logic and validation
* **Repository Layer**: MyBatis mappers for database interactions
