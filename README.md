# Ecommerce Order Notification System

This repository houses a Java Spring Boot project designed to manage the processing of order creation events within an ecommerce microservices ecosystem. The application leverages RabbitMQ to receive order events, facilitates the dissemination of notifications to both customers and merchants, and persistently logs all email communications to a MongoDB instance. Upon successful email delivery, the corresponding mail status within the database is updated to "SENT"; conversely, in cases of failure, it is marked as "FAILED". Furthermore, the application incorporates a scheduled job to automatically retry sending any emails that encountered delivery failures.
## Features

- Receives order creation events via RabbitMQ.
- Sends notifications to customers and merchants.
- Logs all emails to a MongoDB instance.
- Tracks email delivery status in the database.
- Runs a scheduled job to retry sending failed emails.

## Technologies Used

- Java
- Spring Boot
- RabbitMQ
- MongoDB
- Spring Data MongoDB
- Spring Boot Scheduled Tasks

## Prerequisites

Before running the application, make sure you have the following:

- Java Development Kit (JDK) installed on your machine.
- RabbitMQ server running and configured.
- MongoDB instance running and accessible.

## Configuration

To configure the application, follow these steps:

1. Clone the repository to your local machine.

   ````bash
   git clone https://github.com/your-username/ecommerce-order-event-handler.git
   ```

   ````

1. Open the project in your preferred Java IDE.

1. Update the application configuration in the `application.properties` file. Provide the necessary details such as RabbitMQ connection settings, MongoDB connection URI, etc.

   ```
   # RabbitMQ Configuration
   spring.rabbitmq.host=your-rabbitmq-host
   spring.rabbitmq.port=5672
   spring.rabbitmq.username=your-rabbitmq-username
   spring.rabbitmq.password=your-rabbitmq-password

   # MongoDB Configuration
   spring.data.mongodb.uri=mongodb://your-mongodb-uri
   ```

1. Build the project to resolve dependencies and compile the code.

## Usage

Follow these steps to run the application:

1. Ensure that RabbitMQ server and MongoDB instance are up and running.

1. From the root directory of the project, run the following command to start the application:

   ````bash
   ./mvnw spring-boot:run
   ```

   ````

1. The application will start and begin listening for order creation events.

1. As order events are received, the application will send notifications to customers and merchants.

1. The email delivery status will be logged to the MongoDB instance, marking emails as "SENT" or "FAILED" accordingly.

1. The scheduled job will automatically retry sending failed emails based on the configured schedule.



## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [RabbitMQ](https://www.rabbitmq.com/)
- [MongoDB](https://www.mongodb.com/)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
