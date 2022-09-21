# Taboo Cards REST API with Spring Boot

### Summary:

This is simple REST API developed with Spring Boot which allows transactions on taboo cards.

###   Requirements:
* Java (JDK 17 used)
* PostgreSQL (Version 14 used)
* Intellij IDE (Recommended)

###   JSON Format:

```json
{
    "word": "Hece",
    "forbidden": ["Kelime", "Harf", "Ses", "Okumak", "Yazı"]
}
```
**Note:** Example JSON file can found in `src/main/resources/static/words.json`.

---

### Endpoints Table:

| Request URL          | Description                                                                                                             | HTTP   |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------- | ------ |
| /getCardById/{id}    | Returns the card that matches the id.                                                                                   | <code>GET</code>    |
| /getCard/{word}      | Returns the card that matches the word.                                                                                 | <code>GET</code>    |
| /getRandomCard       | Returns a random card.                                                                                                  | <code>GET</code>    |
| /getCards            | Returns all cards.                                                                                                      | <code>GET</code>    |
| /addCard             | Add card to database. If card already exist in the database, it will be updated with new values.                                | <code>POST</code>   |
| /addCards            | Add card list to database. If any card in the list already exist in the database, this card will be updated with new values. | <code>POST</code>   |
| /updateCardById/{id} | Update the card that matches the id.                                                                                    | <code>PUT</code>    |
| /updateCard/{word}   | Update the card that matches the word.                                                                                  | <code>PUT</code>    |
| /deleteCardById/{id} | Delete the card that matches the id.                                                                                    | <code>DELETE</code> |
| /deleteCard/{word}   | Delete the card that matches the word.                                                                                  | <code>DELETE</code> |
| /deleteCards         | Delete all cards.                                                                                                       | <code>DELETE</code> |

---

###   Example GET Request:
####   Request: `/getCard/Avize`
####   Response: `200 - OK`
####   Response Body:
```json
{
    "id": 3,
    "word": "Avize",
    "forbidden" : ["Lamba", "Kristal", "Tavan", "Işık", "Aydınlık"]
}
```
---
###   Example POST Request:
####   Request: `/addCards`
####   Request Body:
```json
[
	{

	"word": "Tiyatro",

	"forbidden" : ["Oyuncu", "Sahne", "Perde", "Oyun", "Suflör"]

	},

	{

	"word": "Kedi",

	"forbidden" : ["Pati", "Fare", "Tüy", "Kuyruk", "Hayvan"]

	}
]
```
####   Response: `201 - CREATED`

####   Response Body:
```json
[
	{
	"id": 1,

	"word": "Tiyatro",

	"forbidden" : ["Oyuncu", "Sahne", "Perde", "Oyun", "Suflör"]

	},

	{
	"id": 2,

	"word": "Kedi",

	"forbidden" : ["Pati", "Fare", "Tüy", "Kuyruk", "Hayvan"]

	}
]
```
---

**Alperen Cubuk**