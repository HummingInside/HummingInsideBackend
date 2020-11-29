# Humming Inside Backend

## API Spec

### Concert list API

`HTTP GET /concerts/`

| Parameter | Type | Description |
|---|---|---|
| status | String | concert progress status (ongoing, upcoming, end) |
| order | String | ordering of list (likes, date) |
| category | Integer | category pk |
| performer | String | performer name |

```json
200 OK
[
  {
    "id": 1,
    "title" : "BTS 2020 Last Concert",
    "performer" : "BTS",
    "date" : "2020-10-27T17:13:40+00:00",
    "description" : "The last concert of BTS in 2020! Enjoy it!",
    "max_audience" : 3000,
    "price" : 20000
  },
  {
    "id": 2,
    "title" : "Twice Comeback Concert",
    "performer" : "Twice",
    "date" : "2020-11-01T09:01:10+00:00",
    "description" : "Twice comes back!",
    "max_audience" : 150,
    "price" : 32000
  }
]
```

<br/>

### Concert detail API

`HTTP GET /concerts/<pk>/`

| Parameter | Type | Description |
|---|---|---|
| pk | Integer | pk of concert |

```json
200 OK
{
  "id": 1,
  "title" : "BTS 2020 Last Concert",
  "performer" : "BTS",
  "date" : "2020-10-27T17:13:40+00:00",
  "description" : "The last concert of BTS in 2020! Enjoy it!",
  "max_audience" : 3000,
  "price" : 20000
}
```

<br/>

### Concert create API

`HTTP POST /concerts/`

| Parameter | Type | Description |
|---|---|---|
| title | String | title of concert |
| date | Date | start date of concert |
| description | String | description of concert |
| max_audience | Integer | limit of max audience count |
| price | Integer | price of concert |

```json
201 Created
{
  "id": 1,
  "title" : "BTS 2020 Last Concert",
  "performer" : "BTS",
  "date" : "2020-10-27T17:13:40+00:00",
  "description" : "The last concert of BTS in 2020! Enjoy it!",
  "max_audience" : 3000,
  "price" : 20000
}
```
<br/>

### Concert update API

`HTTP POST /concerts/<pk>/`

| Parameter | Type | Description |
|---|---|---|
| pk | Integer | pk of concert |
| title | String | title of concert |
| date | Date | start date of concert |
| description | String | description of concert |
| max_audience | Integer | limit of max audience count |
| price | Integer | price of concert |

```json
201 Created
{
  "id": 1,
  "title" : "BTS 2020 Last Concert",
  "performer" : "BTS",
  "date" : "2020-10-27T17:13:40+00:00",
  "description" : "The last concert of BTS in 2020! Enjoy it!",
  "max_audience" : 3000,
  "price" : 20000
}
```

<br/>

### Ticket create API (Reservation)

`HTTP POST /concerts/<pk>/tickets/`

| Parameter | Type | Description |
|---|---|---|
| pk | Integer | pk of concert |

```json
201 Created
{
  "id": 1,
  "concert" : 2,
  "user" : 3,
  "title" : "BTS 2020 Last Concert",
  "performer" : "BTS",
  "date" : "2020-10-27T17:13:40+00:00",
  "description" : "The last concert of BTS in 2020! Enjoy it!",
  "max_audience" : 3000,
  "price" : 20000
}
```

<br/>

### Ticket list API

`HTTP GET /tickets/`

```json
200 OK
[
  {
    "id": 1,
    "concert" : 2,
    "user" : 3,
    "date" : "2020-09-27T17:13:40+00:00"
  },
  {
    "id": 2,
    "concert" : 2,
    "user" : 4,
    "date" : "2020-09-01T17:13:40+00:00"
  }
]
```

<br/>

### Sign In API

`HTTP POST /signin/`

| Parameter | Type | Description |
|---|---|---|
| id | String | id of user |
| password | String | password of user |

```json
200 OK
{
  "id": 1,
  "name" : "Junyoung"
}
```

<br/>

### Sign Out API

`HTTP GET /signout/`

```json
200 OK
```

<br/>

### Sign Up API

`HTTP POST /signup/`

| Parameter | Type | Description |
|---|---|---|
| id | String | id of user |
| password | String | password of user |
| name | String | name of user |
| phone_number | String | phone number of user |
| email | String | email of user |

```json
200 OK
{
  "id": 1,
  "name" : "Junyoung"
}
```
