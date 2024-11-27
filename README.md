# payments-hexagonal
A Spring Boot API developed in Hexagonal Architecture

## Docker compose
```bash
docker-compose up -d
```

## Kafka
* Consume messages for debugging:
```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic payments.info --from-beginning
```

## Requirements
