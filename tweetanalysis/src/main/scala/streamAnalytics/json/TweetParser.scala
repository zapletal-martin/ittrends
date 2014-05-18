package streamAnalytics.json

import streamAnalytics.tweet.{Place, User, Tweet}
import net.liftweb.json._
import net.liftweb.json.DefaultFormats

trait TweetParser {
  def getTweet(data: String): Tweet = {
    implicit val formats = DefaultFormats

    val parent = parse(data)

    for {

      JField("user", JString(user)) <- parent

    } yield (user)

    Tweet("", User("", "", "", 12), "", Some(Place("", "")), Set())
  }
}
