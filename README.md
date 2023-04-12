# Url-Shortner

To create URL shortner entry
POST - http://localhost:8080/create

BODY :
{
"shortUrl" : "qwerty1",
"longUrl" : "https://google.com"
}

To Use Short URL

GET  -  http://localhost:8080/{shortUrl}