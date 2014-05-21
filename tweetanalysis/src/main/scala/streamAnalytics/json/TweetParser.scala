package streamAnalytics.json

import streamAnalytics.tweet.{Place, User, Tweet}
import play.api.libs.json._

trait TweetParser {
  def getTweet(data: String): Tweet = {
    val json = Json.parse(data)
    val user = json \ "user"
    val mentions = json \ "user_mentions"

    def extractUser(userJson: JsValue) = User(
      (user \ "id_str").as[String],
      (user \ "name").as[String],
      (user \ "screen_name").as[String],
      (user \ "followers_count").as[Int])

    def extractMentions(mentionsJson: JsValue) = mentionsJson match {
      case JsArray(elements) => elements.map(extractUser).toSet
      case _ => Set[User]()
    }

    Tweet(
      (json \ "id_str" ).as[String],
      extractUser(user),
      (json \ "text").as[String],
      None,
      extractMentions(mentions))
  }
}
