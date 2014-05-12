package tweetProxy

import akka.actor.{Props, ActorSystem}
import org.ittrends.tweetproxy.core.TweetReaderActor
import org.ittrends.tweetProxy.core.OAuthTwitterAuthorization
import tweetProxy.core.query.ProgrammingTwitterQuery

object Main extends App {

  override def main (args: Array[String]) {
    val system = ActorSystem()

    val streamReader = system.actorOf(
      Props(new TweetReaderActor(TweetReaderActor.twitterUri)
        with OAuthTwitterAuthorization
        with ProgrammingTwitterQuery))

    streamReader ! 'go

    Console.readLine()
    system.shutdown()
  }
}