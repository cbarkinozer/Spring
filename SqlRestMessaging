# Database  
## SQL Commands

![image](https://user-images.githubusercontent.com/43732258/154796297-0041aede-0b3d-435c-b35a-3db249e4ca21.png)  

SQL commands are mainly categorized into five categories: DDL, DQL, DML, DCL, TCL.  
### DDL (Data Definition Language) Commands  
Consists of the SQL commands that can be used to define the database schema.  
**CREATE**
Used to create the database or its objects (like table, index, function, views, store procedure, and triggers).  
Note: 
Database names should not exceed 30 characters.  
Design your database in such a way that you can understand its working principles just by looking at the database.  
```sql
Create table COUNTRY(ID BIGINT, NAME VARCHAR(50));

CREATE TABLE CITY(ID BIGINT,
 NAME VARCHAR(50) NOT NULL,
 PLATE VARCHAR(2) NOT NULL,
COUNTRY_ID BIGINT NOT NULL,
PRIMARY KEY(ID));
```
**DROP**  
Used to delete an existing table in a database. 
```sql
DROP TABLE COUNTRY;
```
**ALTER**  
Used to add, delete, or modify columns in an existing table.  
```sql
ALTER TABLE Customers
ADD Email varchar(255);

ALTER TABLE Customers
DROP COLUMN Email;

ALTER TABLE CITY MODIFY COLUMN PLATE TYPE BIGINT USING PLATE::BIGINT;--POSTGRESQL

ALTER TABLE CITY MODFIY COLUMN PLATE NUMER(19); --ORACLE

```
**TRUNCATE**  
Used to remove all records from a table, but not the table itself.  
```sql
TRUNCATE TABLE CITY;
```
**COMMENT**  
Used to add comments to the data dictionary.
```sql
--Comment
```
**RENAME**  
Used to rename an object existing in the database.  
```sql
ALTER TABLE CITY RENAME CTY_CITY; 
```
### DQL (Data Query Language) Commands 
Used for performing queries on the data within schema objects.  
**SELECT**  
Used to retrieve data from the database.  
```sql
SELECT * FROM COUNTRY;
Select * from CITY;
```
### DML (Data Manipulation Language) Commands
Used to manipulate data present in the database.  
**INSERT**  
Used to insert data into a table.  
```sql
INSERT INTO CITY (NAME,PLATE,COUNTRY)
VALUES ('Adana',01,'Turkey');
````
**UPDATE**  
Used to update existing data within a table.  
```sql
UPDATE CITY
SET COUNTRY='TR'
WHERE PLATE=01;
````
**DELETE**  
Used to delete records from a database table.  
```sql
DELETE FROM CITY
WHERE PLATE=01;
````
**LOCK**
Used to control table concurrency.  
```sql
LOCK [TABLE] [ONLY] table_name [IN lock_mode MODE] [NOWAIT];
LOCK TABLE CITY IN SHARE ROW EXCLUSIVE MODE;
````
**CALL**  
Used to call a PL/SQL or JAVA subprogram.  
```sql
VARIABLE x VARCHAR2(25);
CALL warehouse_type(456, 'Warehouse 456', 2236).ret_name()
   INTO :x;
PRINT x;
X --456
````
**EXPLAIN CALL**  
 Provides a description of how the SQL queries are executed by the databases  
```sql
EXPLAIN (Select * from CITY); 
````

### DCL (Data Control Language) Commands  
Used to deal with the rights, permissions, and other controls of the database system.  
**GRANT**  
Used to give users access privileges to the database.  
```sql
GRANT privilege_name
ON object_name
TO {user_name |PUBLIC |role_name}
[WITH GRANT OPTION];

GRANT SELECT ON employee TO user_admin;
````
**REVOKE**  
Used to withdraw the user’s access privileges given by using the GRANT command.  
```sql
REVOKE privilege_name
ON object_name
FROM {user_name |PUBLIC |role_name};

REVOKE SELECT ON employee FROM user1;
````
### TCL (Transaction Control Language) Commands
**COMMIT**  
Used to commit a transaction manually.  
```sql
COMMIT;
````
**ROLLBACK**  
Used to rollback a transaction in case of any error occurs.  
```sql
ROLLBACK TO SAVEPOINT_NAME;
````
**SAVEPOINT**  
Sets a savepoint within a transaction.  
```sql
SAVEPOINT SAVEPOINT_NAME;
````
**SET TRANSACTION** 
Specify the characteristics for the transaction.  
```sql
SET TRANSACTION READ ONLY;
````
**SET CONSTRAINT**  
The SET CONSTRAINT statement defines, for the current transaction, whether a deferrable constraint is checked after each DML statement or when the transaction is finally committed.  
```sql
SET CONSTRAINT {constraint_name[, ...] | ALL} {DEFERRED | IMMEDIATE};

SET CONSTRAINT ALL IMMEDIATE;
````
**Cross-Origin Resource Sharing (CORS)** 
A web browser-managed mechanism that, using additional HTTP headers, provides access control of a web application running in one origin to a web application in a different origin.  
![image](https://user-images.githubusercontent.com/43732258/155185467-6eaf1641-f6fd-40e7-99a2-95b496280d6e.png)

**Richardson Maturity Model**  
A model (developed by Leonard Richardson) that breaks down the principal elements of a REST approach into three steps.  
These introduce resources, http verbs, and hypermedia controls.  
![image](https://user-images.githubusercontent.com/43732258/155319609-c3db1630-c7ac-4e1b-b421-c0f942fded21.png)  

**Level 0**  
Using HTTP as a transport system for remote interactions.  
Basically using HTTP as a tunneling mechanism for your own remote interaction mechanism, usually based on Remote Procedure Invocation.  
![image](https://user-images.githubusercontent.com/43732258/155319917-689bdfb6-f1c7-440f-ad18-9b9bf2fedc44.png)  

**Level 1 - Resources**  
Rather than making all our requests to a singular service endpoint, we start talking to individual resources.  
Tackles the question of handling complexity by using divide and conquer, breaking a large service endpoint down into multiple resources.  
![image](https://user-images.githubusercontent.com/43732258/155320095-14ce3b15-46aa-4917-b589-ae1159378524.png)  

**Level 2 - HTTP Verbs**  
Using the HTTP verbs as closely as possible to how they are used in HTTP itself.  
Introduces a standard set of verbs so that we handle similar situations in the same way, removing unnecessary variation.  
![image](https://user-images.githubusercontent.com/43732258/155320297-9391278b-fe51-4751-b2ff-9239ebd9b86c.png)  

**Level 3 - Hypermedia Controls**  
The final level introduces HATEOAS (Hypertext As The Engine Of Application State).  
HATEOAS addresses the question of how to get from a list open slots to knowing what to do to book an appointment.  
Introduces discoverability, providing a way of making a protocol more self-documenting.  
![image](https://user-images.githubusercontent.com/43732258/155320558-4cd3318c-8650-44d7-8421-31536541c5f7.png)

**Message Queue**  
Message queues are used to forward a message received from one source (service, application, etc.) to another source.  
It works with the principle of keeping the data in the queue and then writing it to the desired source, in order to prevent blockages that may occur during the processing of large numbers of data, especially in systems with heavy data flow.  

**Message Broker**  
A message broker is an architectural pattern for message validation, transformation, and routing. It mediates communication among applications, minimizing the mutual awareness that applications should have of each other in order to be able to exchange messages, effectively implementing decoupling.  
The primary purpose of a broker is to take incoming messages from applications and perform some action on them.  
Sequence diagram for depicting the Message Broker pattern:  
![image](https://user-images.githubusercontent.com/43732258/155321959-64850a56-bd17-4eb3-8ca4-3da63d9337c9.png)  

RabbitMQ is one of the most used open source 'message broker' structures.  
Apart from RabbitMQ, different message queue tools such as Apache Kafka, MSMQ, and ActiveMQ are also frequently used today.  
Kafka is ideal for big data use cases that require the best throughput, while RabbitMQ is ideal for low latency message delivery, guarantees on a per-message basis, and complex routing.  

Producer is the message sender, and consumer is the message receiver.  

**Queue**  
Region where messages are kept, the size of which depends on the disk capacity.  
Multiple producers can send many messages to a single queue.  
Multiple consumers can receive and process messages from a single queue.  

**Connection**: A TCP connection between your application and the RabbitMQ broker.  

**Channel**: A virtual connection inside a connection. When publishing or consuming messages from a queue - it's all done over a channel.  

**Exchange**: Receives messages from producers and pushes them to queues depending on rules defined by the exchange type. To receive messages, a queue needs to be bound to at least one exchange.  

**Binding**: A binding is a link between a queue and an exchange.  

**Routing key**  
A key that the exchange looks at to decide how to route the message to queues.  
Think of the routing key like an address for the message.  

**Advanced Message Queuing Protocol(AMQP)**: Protocol used by RabbitMQ for messaging.  

**Users**  
It is possible to connect to RabbitMQ with a given username and password.  
Every user can be assigned permissions such as rights to read, write and configure privileges within the instance.  
Users can also be assigned permissions for specific virtual hosts.  

**Virtual host(vhost)**  
Provides a way to segregate applications using the same RabbitMQ instance.  
Different users can have different permissions to different vhost and queues and exchanges can be created, so they only exist in one vhost.  


Producer does not send the message directly to the queue.  
It sends the message to the exchange.  
Exchange learns which queue to go to with terms defined as binding key and routing key, and the message goes to that queue.  
![image](https://user-images.githubusercontent.com/43732258/155323013-0696831c-ab36-40f7-8353-080838e64e5c.png)  

**Direct**  
The message is routed to the queues whose binding key exactly matches the routing key of the message.  
For example, if the queue is bound to the exchange with the binding key pdfprocess, a message published to the exchange with a routing key pdfprocess is routed to that queue.  

**Fanout**: A fanout exchange routes messages to all of the queues bound to it.

**Topic**: The topic exchange does a wildcard match between the routing key and the routing pattern specified in the binding.

**Headers**: Headers exchanges use the message header attributes for routing.
![image](https://user-images.githubusercontent.com/43732258/155324105-d8875325-1c86-4c75-ae0e-4e77fd090915.png)  

**RabbitMQ HelloWorld in Java**  
To use RabbitMQ, you must first have an active RabbitMQ server. You can either install a separate RabbitMQ or use it with a Docker image(Default username and password: guest).  

![image](https://user-images.githubusercontent.com/43732258/155325762-924974b1-2ae9-47a0-8346-33cbfa2c88bb.png)  

Sender   
```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
```

Receiver  
```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}

```


**TestRestTemplate**  

**References**:
https://www.geeksforgeeks.org/sql-ddl-dql-dml-dcl-tcl-commands/
https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS  
https://martinfowler.com/articles/richardsonMaturityModel.html  
https://www.baeldung.com/spring-boot-testresttemplate  
https://en.wikipedia.org/wiki/Message_broker  
https://www.rabbitmq.com/tutorials/tutorial-one-java.html  
