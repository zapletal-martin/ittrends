package streamAnalytics.json

import streamAnalytics.tweet.{Place, User, Tweet}
import net.liftweb.json._
import net.liftweb.json.DefaultFormats

trait TweetParser {
  def getTweet(data: String): Tweet = {
    implicit val formats = DefaultFormats

    val parent = parse(data)

    val JString(id) = parent \ "id_str"

    val user = parent \ "user"
    val JString(userId) = user \ "id_str"
    val JString(name) = user \ "name"
    val JString(screenName) = user \ "screen_name"
    val JInt(followers) = user \ "followers_count"

    Tweet(id, User(userId, name, screenName, followers.toInt), "", Some(Place("", "")), Set())
  }
}
