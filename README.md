# What's in my fridge?

## Code Status
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![CircleCI](https://circleci.com/gh/Rugal/fridge.svg?style=svg)](https://circleci.com/gh/Rugal/fridge)  

[![Heroku](http://heroku-badge.herokuapp.com/?app=fridge-developmen&svg=1&root=/actuator/info)](https://fridge-development.herokuapp.com/actuator/info)

## Start Local Environment

### Database
Please install `docker` and `docker-compose`.  

At the root directory run the following command:  

```bash
docker-compose -f configuration/docker/docker-compose.yml up
```

The default user is `postgres`, password is `123`


### Service

Please install `maven` and `JDK 11`.  

You should also initialize the database by flyway, if you haven't done so:

```bash
mvn flyway:migrate
```

At the root directory run the following command:  
```bash
mvn spring-boot:run
```

By default it will connect to the docker database.  
