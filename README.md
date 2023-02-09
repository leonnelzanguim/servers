# Getting started

## requirement

- docker
- docker-compose
- git
- maven
- java
- node
- npm
- angular cli

## running the app

### cloning the repository
```
$ git clone https://github.com/leonnelzanguim/servers.git
```
### buid backend
```
$ cd Backend
$ mvn package
```
### build and run backend docker image
```
$ cd Backend
$ docker build -t backend_image_name -f Docker/Dockerfile .
$ docker run --rm -p 8090:8090 --name backend backend_image_name
```

the backend is available at : http://localhost:8090/

### buid frontend
```
$ cd Frontend
$ npm install
$ npm run start
```
### build and run frontend docker image
```
$ cd Frontend
$ docker build -t frontend_image_name -f Docker/Dockerfile .
$ docker run --rm -p 4200:80 --name frontend frontend_image_name
```
The frontend is available at: http://localhost:4200/

## Using docker-compose

Alternatively we can start both the backend and the frontend with docker-compose.
This we use the backend and frontend image from DockerHub.

```
$ docker-compose up
```

# TODO

- update the docker-compose file for the communication between the 2 containers.
- better comment and organize the code
