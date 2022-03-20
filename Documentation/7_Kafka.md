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
**References**: 
https://www.youtube.com/watch?v=ZphPT3r6fnU  
