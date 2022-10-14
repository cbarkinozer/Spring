Kafka Fundamentals
Fundamentals of the Apache Kafka

What is Apache Kafka?
Kafka is a fault-tolerant and horizontally scalable distributed streaming framework. [1]

What is a distributed system?
A distributed system is one in which several computing resources collaborate towards a common goal. [1]
Kafka is distributed in the sense that it stores, receives, and delivers messages across a network of nodes known as brokers.
[1]

What does horizontally scalable mean?
Horizontal scalability refers to the ability to enhance capacity by linking various computer resources so that they function as a single unit. [1]
There is no downtime required to add a new computer resource, and there are no computing resource constraints. [1]

What does fault tolerance mean?
Nodes in Kafka can fail, and the system can make calibrated modifications and continue to function. However, the more fault-tolerant your system, the lower its performance. [1]

**How Kafka works?**  
Kafka is more than just a messaging system. In a messaging system, a producer sends a message to a queue, and a consumer consumes that message. Kafka has data streams, and clusters, and operates in real-time. [2]  
Kafka saves data on disk, similar to a database, for subsequent recovery if necessary. Unlike a database, however, Kafka deletes messages after a certain period of time. [2]  

If no key is provided while creating a message, the message is transmitted through the round-robin scheduling mechanism, and each segment receives a message (even if they were sent by the same producer). As a result, we cannot ensure the delivery order at the partition level; if we always wish to send messages to the same partition, we must encrypt our messages. This guarantees that the message is always sent to the same partition. [2]  

![image](https://user-images.githubusercontent.com/43732258/195947464-7b71f732-6d01-45b0-ad48-7484f2243486.png)  

Round Robin Scheduling Algorithm[3]  

The benefits of round-robin scheduling include the best average reaction time, which is ideal for interactive systems. [3]  
The disadvantages of round-robin scheduling include that there is no task priority, performance is dependent on time quantum, and there is a possibility of hunger for processes with longer burst lengths because the cycle is repeated multiple times.[3]  

The architecture of Apache Kafka is made up of topics, partitions, brokers, producers, and consumers.[2]

![image](https://user-images.githubusercontent.com/43732258/195947489-42d88fb4-8f7d-41f2-8041-2d3eb858f9d7.png)  

Apache Kafka Architecture [4]  
![image](https://user-images.githubusercontent.com/43732258/195947508-c2d9512c-0c2c-4e6e-881f-6c54038178af.png)  

Apache Kafka Inside of a Broker [3]  

**What are Kafka's Topics?**  
Kafka topics are similar to virtual topics in that they arrange messages or events in a logical sequence (first in first out order). The contents of the subjects remain unchangeable. [1]  

**What are Kafka Partitions?**  
For the sake of parallelism, each subject inside a broker is divided into partitions that are sorted by time. [1]  

**What is an offset in Kafka?**  
Each message within a partition is assigned an incremental id known as an offset. [1]  

In Kafka, data is only retained for a certain duration, by default one week; beyond that time, the data and offset values are erased. [5]  

When a consumer consumes a message, it automatically publishes offset data to the subject “__consumer offsets.” [5]  

**What are Kafka Clusters?**  
Kafka Clusters are made up of multiple brokers. [1]

**What are Kafka Brokers?**  
Kafka Brokers are self-contained computing resources that exist in Kafka Clusters. They do necessary computations and communicate with one another to manage replication and acknowledgments (acks). [1]

**What are Kafka's Acknowledgments?**  
The ack is essentially a confirmation that the message was successfully sent. We may customize this ack when creating messages with three distinct levels in Kafka.[2]  
ack = 0: The ack from Kafka was not received. The message will be lost if the broker fails. [2]  

ack = 1: The default setting. Only the partition’s leader receives an acknowledgment. The data will only be lost if the leader fails, which is still a possibility. [2]  
ack = all: The most dependable setup because there is no risk of data loss. Receiving acknowledgment from both the leader and the replicas. If a single replica is out of sync, Kafka will wait for the sync before returning the ack. [2]  

**What is the Kafka Commit Log?**  
A commit log is a persistent-ordered data structure that only allows appends and does not allow change or deletion. Because this data structure obtains data by record id, reads and writes do not lock each other, and reads and writes have O(1) complexity. This allows Kafka to operate in the same manner regardless of data size under 10 ms. [4]  

**What are Kafka Producers?**  
Using the Kafka producer API, an array of bytes is transmitted to the Kafka user’s topic. [1]  

Code example[1]:  

```java
//create Producer properties                                     
String bootstrapServers = ("127.0.0.1:9092");                                    
Properties properties = new Properties();                                    
properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);                                     properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());                                     properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());                                       
 
// create the producer                                    
KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);                                          
 
//create a Producer record                                     
ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "hello world");                                     
 
// send data                                   
producer.send(record);                                     p
roducer.close();
```

Code example[1]:

```java
Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

        String bootstrapServers = "localhost:9092";
        String groupId = "kafka-consumer-application";
        String topic = "first_topic";
        Properties properties = new Properties();

        // create consumer configs                                     properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);                                     properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());                                     properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());                                     properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);                                     properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");                                                                    

        //create consumer                                     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);                                     

        //subscribe consumer to our topic(s)                                     consumer.subscribe(Arrays.asList(topic));                                      

        // poll for new data                                                                   while (true) {                                          
        ConsumerRecords < String, String > records =
            consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord < String, String > record: records) {
            logger.info("key: " + record.key() + "Value: " + record.value());
            logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
        }
    }
```

**What is Kafka Broker Discovery?** 
A bootstrap server is Kafka Broker Discovery. With Kafka Broker Discovery, you only need to connect to one broker to have access to the whole cluster because each broker knows everything about the others. [6]  

**What is Apache Zookeeper?** 
Zookeeper monitors Kafka brokers, assists in broker leader selection for topic partitions, and alerts brokers when a new broker is activated or deactivated, as well as when a topic is generated or destroyed. [6]  
Because one of the Zookeepers becomes the leader and the others become followers, the number of Zookeepers in the cluster must be an odd number.
[6]  

Apache Zookeeper is discontinued after version 2.8.x since Kafka includes a built-in authorizer called StandardAuthorizer that does not rely on Zookeeper. Instead of ZooKeeper, metadata will be saved in a partition within Kafka (in Kafka). [6]  

**What are Kafka Topic Replications?** 
Kafka is a fault-tolerant distributed system. This fault tolerance occurs because a copy of each partition is preserved on a separate broker known as a topic replicant. In general, the replication count should not be less than three. [6]  
Each partition of a subject in a broker is the partition’s leader, and each partition may only have one leader. Each subject in a broker has only one leader partition. The messages are only received by the leader; the clones just sync the data. As a result, even if a replicant fails, data is still retained. When a leader dies, the Zookeeper will automatically elect a clone as the next leader. [2]  

**Needed Docker commands to run Kafka on Docker:[8]**  

docker run --name kafka -p 9092:9092 -e  
KAFKA_ZOOKEEPER_CONNECT=172.26.48.1:2181 -e    
KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.26.48.1:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka  
```docker
version: "3"
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 101
      KAFKA_ADVERTISED_HOST_NAME: localhost
      KAFKA_ADVERTISED_PORT: 9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    depends_on:
      - zookeeper
``` 


You can check the resources for more.

Thank you for reading. Please do not forget to clap if you think it was helpful.

Resources
[1] Jordan Romero,(27 March 2021), An Introduction to Apache Kafka:

[https://medium.datadriveninvestor.com/an-introduction-to-apache-kafka-43eaad561198]

[2] João Guilherme Berti Sczip,(2 Mar 2021), Apache Kafka: What is and how it works?

[https://medium.com/swlh/apache-kafka-what-is-and-how-it-works-e176ab31fcd5]

[3] Akshay Singhal, (13 October 2022), Round Robin Scheduling Examples:

[https://www.gatevidyalay.com/round-robin-round-robin-scheduling-examples/]

[4]javatpoint, (9,October 2022), Apache Kafka Architecture:

[https://www.javatpoint.com/apache-kafka-architecture]

[5] Lovisa Johansson, (19,March 2022), Part 1: Kafka for Beginners What is Apache Kafka:

[https://www.cloudkarafka.com/blog/part1-kafka-for-beginners-what-is-apache-kafka.html]

[6] Serkan Eren, (6, May, 2019), Apache Kafkaya Giriş:

[https://medium.com/devopsturkiye/apache-kafkaya-giri%C5%9F-2-9455e45d4c0f]

[7]Stanislav Kozlovski,(15 December 2017), A Thorough Introduction To Apache Kafka:

[https://betterprogramming.pub/thorough-introduction-to-apache-kafka-6fbf2989bbc1]

[8] kablosuzkedi, (11 May 2022), 1 Videoda #Apache Kafka Nedir? Apache Kafka Neden Kullanılır? Apache Kafka Nasıl Kurulur?
[https://www.youtube.com/watch?v=ZphPT3r6fnU ]
