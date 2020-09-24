# Humming Inside Backend

## API Spec

### Concert list API

`HTTP GET /concerts/`

| Parameter | Type | Description |
|---|---|---|
| status | String | concert progress status (ongoing, upcoming, end) |
| order | String | ordering of list (likes, date) |
| category | Integer | category pk |

<br/>

### Concert detail API

`HTTP GET /concerts/<pk>/`

| Parameter | Type | Description |
|---|---|---|
| pk | Integer | concert pk |

<br/>

### Ticket list API

`HTTP GET /tickets/`

<br/>

### Concert create API

`HTTP POST /concerts/`

| Parameter | Type | Description |
|---|---|---|
| title | String | title of concert |
| date | Date | start date of concert |
| place | String | place of concert |
| description | String | description of concert |
| max_audience | Integer | limit of max audience count |
| price | Integer | price of concert |

<br/>

### Concert update API

`HTTP POST /concerts/<pk>/`

| Parameter | Type | Description |
|---|---|---|
| pk | Integer | concert pk |
| title | String | title of concert |
| date | Date | start date of concert |
| place | String | place of concert |
| description | String | description of concert |
| max_audience | Integer | limit of max audience count |
| price | Integer | price of concert |

<br/>

### Sign In API

`HTTP POST /signin/`

| Parameter | Type | Description |
|---|---|---|
| id | String | id of user |
| password | String | password of user |

<br/>

### Sign Out API

`HTTP GET /signout/`

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
