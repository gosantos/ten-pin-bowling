[![CircleCI](https://circleci.com/gh/gosantos/ten-pin-bowling/tree/master.svg?style=svg)](https://circleci.com/gh/gosantos/ten-pin-bowling/tree/master)

# Ten Pin Bowling

https://en.wikipedia.org/wiki/Ten-pin_bowling

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* [Java 8](https://sdkman.io/usage)

### Installing

```
$ git clone https://github.com/gosantos/ten-pin-bowling

$ cd ten-pin-bowling

$ ./gradlew clean build

$ ./gradlew bootRun

$ curl https://localhost:8080/scores/1
```

## Running the tests

Automated tests

```
$ ./gradlew test
```

You can also execute some manual tests using the script below:

```
$ ./test.sh
```

### Docs

API Docs were built using Swagger. You can see it on the link below.

[API Documentation](https://stark-tor-77061.herokuapp.com/swagger-ui.html)

### Test approach

I followed the pyramid testing approach.

<img alt="Test Pyramid" src="https://raw.githubusercontent.com/gosantos/ten-pin-bowling/master/testPyramid.jpg" height="200">


Most of the tests are unitary. (28 tests)

Repositories and API Tests are integration tests. (9 tests)

EndToEndTests are running the application, making requests and interacting with the database. (3 tests)  

## Deployment


The application is deployed on Heroku.
 
Every commit on the master branch triggers a circle ci build,
after the build succeeds, the application is deployed.

## Usage

``` 
curl -H "Content-Type: application/json" -X POST  https://stark-tor-77061.herokuapp.com/games/

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "9" }' https://stark-tor-77061.herokuapp.com/games/{gameId}/rolls
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "1" }' https://stark-tor-77061.herokuapp.com/games/{gameId}/rolls

curl https://stark-tor-77061.herokuapp.com/scores/{gameId}
```
