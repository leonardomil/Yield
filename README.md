# Yield
Api test

Here are some examples of requests:
```
curl --location --request POST 'localhost:8080/user/accreditation' \
--header 'Content-Type: application/json' \
--data-raw '{
  "user_id": "g8NlYJnk7zK9BlB1J2Ebjs0AkhCTpE1V",
  "payload": {
    "accreditation_type": "BY_INCOME",
    "documents": [{
        "name": "2018.pdf",
        "mime_type": "application/pdf",
        "content": "ICAiQC8qIjogWyJzcmMvKiJdCiAgICB9CiAgfQp9Cg=="
      },{
        "name": "2019.jpg",
        "mime_type": "image/jpeg",
        "content": "91cy1wcm9taXNlICJeMi4wLjUiCiAgICB0b3Bvc29ydCAiXjIuMC4yIgo="
      }
    ]
  }
}'


curl --location --request GET 'localhost:8080/user/accreditation/'

curl --location --request GET 'localhost:8080/user/accreditation/g8NlYJnk7zK9BlB1J2Ebjs0AkhCTpE1V'
```
