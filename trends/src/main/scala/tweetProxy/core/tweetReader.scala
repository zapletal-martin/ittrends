package org.ittrends.tweetproxy.core

import spray.http._
import spray.client.pipelining._
import akka.actor.{Props, ActorRef, Actor}
import spray.http.HttpRequest
import spray.can.Http
import akka.io.IO
import org.ittrends.tweetProxy.core.TwitterAuthorization
import tweetProxy.core.query.{TwitterStreamApiRequest, TwitterQuery}
import tweetProxy.output.KafkaProducerActor
import tweetProxy.output.KafkaProducerActor.Produce
import tweetProxy.output.kafkaIntegration.KafkaProducer
import akka.routing.SmallestMailboxRouter

object TweetReaderActor {
  val twitterUri = Uri("https://stream.twitter.com/1.1/statuses/filter.json")
}

class TweetReaderActor(uri: Uri) extends Actor with TwitterStreamApiRequest  {
  this: TwitterQuery with TwitterAuthorization =>

  //set based on cpu concurrency level
  val producers = context.actorOf(Props(new KafkaProducerActor(new KafkaProducer())).withRouter(SmallestMailboxRouter(4)))

  def receive: Receive = {
    case 'go  => twitterStreamingApiRequest(uri, keywords, following)
    case ChunkedResponseStart(_) => System.out.println("Twitter streaming connection open")
    case ChunkedMessageEnd(_, _) => System.out.println("Twitter streaming connection closed")
    case MessageChunk(entity, _) => producers ! Produce(entity.asString)
  }
}