# Welcome!

Hi there! I'm Harikaran.🎉

Checkout my new CRM backend app. Here, I have provided all the API's with it's instructions.
You can further inspect it. I'm very happy to share my process here :).

Let's get started,

**Base URL:** [https://crm-server-0zlr.onrender.com](https://crm-server-0zlr.onrender.com)

> Note - To check all API's. Please provide base url before every path.

# Login API

Here you have the credentials to check out my CRM Backend app:

**API Route:** - /login

**Method** - POST

**Username:** `hari7@gmail.com`

**Password:** `hari123`

> NOTE - After successful login, you will get JWT Token. Please attach that token to do the further operations with API's.


# Register API

Here, in this way you can register new user

**API Route:** - /register

**Method** - POST

```json
{
    "firstName": "your first name",
    "lastName": "your last name",
    "username": "your email",
    "password": "your password",
    "role": "ADMIN"
}

```

# Customers API

## Get All Customers

**API Route:** - /customers/all

**Method** - GET

## Get A Specific Customer

**API Route:** - /customers/2

**Method** - GET

## Create New Customer

**API Route:** - /customers/create

**Method** - POST

```json

{
    "name":"customer name",
    "email":"customer email",
    "phone": 9876543210,
    "address":{
        "doorNo": 0,
        "streetName": "",
        "city": "",
        "district": "",
        "state": "",
        "pincode": 111111
    }
}

```

## Update Customer

**API Route:** - /customers/update/5

**Method** - PUT

Accepted Regions - "SOUTH", "NORTH", "EAST", "WEST"

```json

{
    "name": "",
    "email": "",
    "phone": 9876543210,
    "address": {
        "doorNo": 0,
        "streetName": "",
        "city": "",
        "district": "",
        "state": "",
        "pincode": 111111,
        "region": "SOUTH"
    }
}

```

## Deleting a Customer

**API Route:** - /customers/7

**Method** - DELETE

# Leads API

## Get All Leads

**API Route:** - /leads/all

**Method** - GET

## Get A Specific Lead

**API Route:** - /leads/3

**Method** - GET

## Create New Lead

**API Route:** - /leads/create

**Method** - POST

```json

{
    "name": "",
    "email": "",
    "phone": 1234567890,
    "qualified": false,
    "address": {
        "doorNo": 0,
        "streetName": "",
        "city": "",
        "district": "",
        "state": "",
        "pincode": 666666
    }
}

```

## Update Lead

**API Route:** - /leads/update/11

**Method** - PUT

Accepted Regions - "SOUTH", "NORTH", "EAST", "WEST"

```json

{
    "name": "",
    "email": "",
    "phone": 9876543210,
    "qualified": false,
    "address": {
        "doorNo": 0,
        "streetName": "",
        "city": "",
        "district": "",
        "state": "",
        "pincode": 111111,
        "region": "SOUTH"
    }
}

```

## Deleting a Lead

**API Route:** - /leads/10

**Method** - DELETE

# Sales Reports API

## Get Yearly Sales

**API Route:** - /sales_reports/yearly/2023

**Method** - GET

## Get Monthly Sales For the Specific Year

**API Route:** - /sales_reports/monthly/2021

**Method** - GET

## Get Regional Sales

**API Route:** - sales_reports/regional

**Method** - GET

## Get Quarterly Sales Results

**API Route:** - /sales_reports/quarterly

**Method** - GET

# Feedback API

## Get Feedback Trends

**API Route:** - /feedback/get_trends/3-year

**Method** - GET

Accepted Values - [1-year, 2-year, 3-year, 6-months]

## Get Feed Feedback Statistics

**API Route:** - /feedback/statistics
**Method** - GET


