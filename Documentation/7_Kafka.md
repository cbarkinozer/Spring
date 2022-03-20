docker run --name kafka -p 9092:9092 -e  
KAFKA_ZOOKEEPER_CONNECT=172.26.48.1:2181 -e    
KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.26.48.1:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka  

**References**: 
https://www.youtube.com/watch?v=ZphPT3r6fnU  
