package tweetProxy.output.kafkaIntegration

import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import java.util.Properties
import org.ittrends.tweetproxy.core

class KafkaProducer {

  val Producer = Prod()

  def Produce(tweet: String) = {
    System.out.println(tweet)
    Producer.send(new KeyedMessage[String, String]("tweets", tweet))
  }

  private def Prod() = {
    val props = new Properties()
    props.put("metadata.broker.list", "ubuntu.ubuntu-domain:9092")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("partitioner.class", "tweetProxy.output.kafkaIntegration.KafkaProducerPartitioner")
    props.put("request.required.acks", "0")

    new Producer[String, String](new ProducerConfig(props))
  }


}
