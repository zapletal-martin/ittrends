#!/usr/bin/env bash

kafka_2.10-0.8.1.1/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic tweets --from-beginning
