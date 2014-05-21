package streamAnalytics.json

import streamAnalytics.tweet.{Place, User, Tweet}
import play.api.libs.json._

trait TweetParser {
  def getTweet(data: String): Tweet = {
    val json = Json.parse(data)
    val user = json \ "user"
    val mentions = json \ "entities"\ "user_mentions"

    def extractUser(userJson: JsValue) = extractUserDefault(userJson, (userJson \ "followers_count").as[Int])

    def extractUserDefault(userJson: JsValue, followersDefault: Int) = User(
      (userJson \ "id_str").as[String],
      (userJson \ "name").as[String],
      (userJson \ "screen_name").as[String],
      followersDefault)

    def extractMentions(mentionsJson: JsValue) = mentionsJson.as[Set[JsValue]].map(extractUserDefault(_, 0))

    Tweet(
      (json \ "id_str" ).as[String],
      extractUser(user),
      (json \ "text").as[String],
      None,
      extractMentions(mentions))
  }
}
