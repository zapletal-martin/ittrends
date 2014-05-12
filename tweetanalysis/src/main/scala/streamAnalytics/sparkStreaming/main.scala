package streamAnalytics.sparkStreaming

import akka.actor.{Props, ActorSystem}

object Main extends App {
  override def main(args: Array[String]) = {
    val analytics = new KeywordCount();
    analytics.Run()
  }
}

