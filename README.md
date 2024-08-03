# Simple Email Sender Application - Java Spring Microservices

### Application Details
This application consistis of two microservices: user ms and email ms.
Both microservices operates with different databases (PostgreSQL), communicating asynchronously through a RabbitMQ exchange / queue implemented with CloudAMQP.

### Application Flow
The user microservice exposes an endpoint for registering a user, receiving it's name and email address. Once the user data is saved into the database, a producer publishes a message to the RabbitMQ default exchange.

The email microservice implements a producer which listens to messages redirected to the RabbitMQ queue through the default exchange. It then sends the email to the newly registered user and saves the email information into the database.

Technologies:

* Spring Framework
* PostgreSQL
* Spring AMQP
* Java Mail Sender
* RabbitMQ
* CloudAMQP