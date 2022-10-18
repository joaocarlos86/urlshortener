# URL Shortener

## About

This simple project can shorten long URLs into a minified version of the URL so that it is easier to share.
All shortened URLs are stored in an in-memory database (H2 Database).

### How it works

A user submits a post request containing the URL to be shortened, if the request is valid, the application will generate 
a hash (with 5 characters) for that URL and save it in the database, then it will return the hash that can be used to 
resolve the token into the long URL again.

#### How is the hash generated?

See [RandomWordService](src/main/java/com/example/urlshortener/shortener/service/RandomWordService.java).

The algorithm is to use generate a word where each character is randomly selected from a sequence of alphanumeric characters.
The total amount of unique hash is given by:

```
number of unique hash = t ^ p; where
t = number of characters available in the alphanumeric sequence
p = number of characters in the hash

For this application, t = 62 and p = 5, so:

62 ^ 5 = 916132832 
```

In case of a hash collision (i.e. a randomly generated hash is already in use), the application will try to generate a 
new hash up to 5 times. If the application doesn't succeed it will return a 500 HTTP error 
(See [ShortenerService](src/main/java/com/example/urlshortener/shortener/service/ShortenerService.java)).

### Built with

* Apache Maven
* Java 17
* Spring Boot
* Spring Webflux
* Spring Retry
* Spring Data JPA
* H2 Database
* Apache Commons Validator


## How to build

```bash
mvn clean verify

# or 
# mvn clean install
# if you want the artefact to be installed in your local maven repository
```

## How to run

```bash
mvn spring-boot:run
```

After running the above command, the application will be available on `http://localhost:8080`.
The API can be browsed via swagger, it is available at `http://localhost:8080/swagger-ui/index.html`

## API

The application exposes two endpoints:

| Controller                                                                                                  | Operation                                           | HTTP Method | Path               | Parameters                                                                                                          | Response                                                                                                                |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------------|-------------|--------------------|---------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| [ShortenerController](src/main/java/com/example/urlshortener/shortener/controller/ShortenerController.java) | Create a new short URL                              | POST        | /shortener         | [CreateShortURLRequest](src/main/java/com/example/urlshortener/shortener/controller/dto/CreateShortURLRequest.java) | [CreateShortURLResponse](src/main/java/com/example/urlshortener/shortener/controller/dto/CreateShortURLResponse.java)   |
| [ShortenerController](src/main/java/com/example/urlshortener/shortener/controller/ShortenerController.java) | Resolve an existing short URL into the long version | GET         | /shortener/{token} | String                                                                                                              | [ResolveShortURLResponse](src/main/java/com/example/urlshortener/shortener/controller/dto/ResolveShortURLResponse.java) |

### Examples

#### Shorten a URL:

```bash
curl -X POST \
  http://localhost:8080/shortener \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
    "url": "www.google.com"
}'
```

Response:

```json
{
    "originalUrl": "www.google.com",
    "shortUrl": "5myjt"
}
```

#### Resolve a short URL

```bash
curl -X GET \
  http://localhost:8080/shortener/5myjt \
  -H 'cache-control: no-cache'
```

Response:

```json
{
    "originalUrl": "www.google.com",
    "shortUrl": "5myjt"
}
```

## Contracts

This application uses contract testing ([Spring Cloud Contract](https://spring.io/projects/spring-cloud-contract)). The
application build cycle will make sure the API is in conformity with the contracts defined so that no unexpected changes
are introduced and clients are not disrupted.

Also, once successfully building the application, a stub will be generated, allowing for decoupled integration testing in 
consumers of this service.

To run the stubs using docker, please use the following:

```bash

SC_CONTRACT_DOCKER_VERSION="3.1.4"
STUBRUNNER_PORT="8083"
STUBRUNNER_IDS="com.example:urlshortener:+:stubs:9876"

docker run --rm  \
  -e "STUBRUNNER_IDS=${STUBRUNNER_IDS}"  \
  -e "STUBRUNNER_STUBS_MODE=LOCAL" \
  -p "${STUBRUNNER_PORT}:${STUBRUNNER_PORT}"  \
  -p "9876:9876"  \
  -v "${HOME}/.m2/:/home/scc/.m2:rw" \
  springcloud/spring-cloud-contract-stub-runner:"${SC_CONTRACT_DOCKER_VERSION}"

```