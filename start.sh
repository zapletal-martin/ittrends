#!/usr/bin/env bash

#
kafka_2.10-0.8.1.1/bin/zookeeper-server-start.sh kafka_2.10-0.8.1.1/config/zookeeper.properties &
kafka_2.10-0.8.1.1/bin/kafka-server-start.sh kafka_2.10-0.8.1.1/config/server.properties &