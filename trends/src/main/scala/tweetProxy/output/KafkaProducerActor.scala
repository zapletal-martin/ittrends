package tweetProxy.output

import akka.actor.{PoisonPill, Actor}
import tweetProxy.output.KafkaProducerActor.Produce
import tweetProxy.output.kafkaIntegration.KafkaProducer

object KafkaProducerActor {
  case class Produce(tweet: String)
}

class KafkaProducerActor (producer: KafkaProducer) extends Actor {
  override def receive: Actor.Receive = {
    case Produce(tweet) =>
      producer.Produce(tweet)
  }
}
