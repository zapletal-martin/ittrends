package tweetProxy.output.kafkaIntegration

import kafka.producer.Partitioner
import kafka.utils.VerifiableProperties

//TODO: implement logic
class KafkaProducerPartitioner extends Partitioner[String]{

  def this (props: VerifiableProperties) = this()

  override def partition(key: String, numPartitions: Int): Int = {
    0
  }
}
