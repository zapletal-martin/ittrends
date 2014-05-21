import org.specs2.mutable._
import streamAnalytics.json.TweetParser

class jsonParsing extends Specification with TweetParser {
  lazy val data =
    """{"created_at":"Sun May 18 10:01:13 +0000 2014",
      | "id":467968138488082432,"id_str":"467968138488082432",
      | "text":"@FabRavezzani @RobertoRenga scala dei valori #antijuventina per eccellenza.complimenti a loro.",
      | "source":"\u003ca href=\"http:\/\/twitter.com\/#!\/download\/ipad\" rel=\"nofollow\"\u003eTwitter for iPad\u003c\/a\u003e",
      | "truncated":false,"in_reply_to_status_id":467904915189538816,"in_reply_to_status_id_str":"467904915189538816",
      | "in_reply_to_user_id":598536444,"in_reply_to_user_id_str":"598536444","in_reply_to_screen_name":"FabRavezzani",
      | "user":{"id":239995975,"id_str":"239995975","name":"cillo#32!\uf8ff","screen_name":"cillo70","location":"Fano",
      | "url":null,"description":"se non in questa, sara' nella prossima","protected":false,"followers_count":150,
      | "friends_count":717,"listed_count":0,"created_at":"Tue Jan 18 22:48:43 +0000 2011","favourites_count":1958,
      | "utc_offset":7200,"time_zone":"Rome","geo_enabled":true,"verified":false,"statuses_count":1786,"lang":"it",
      | "contributors_enabled":false,"is_translator":false,"is_translation_enabled":false,
      | "profile_background_color":"0F0A02","profile_background_image_url":"http:\/\/pbs.twimg.com\/profile_background_images\/745752143\/66b218fd7040a28f7edaba70457417a2.jpeg",
      | "profile_background_image_url_https":"https:\/\/pbs.twimg.com\/profile_background_images\/745752143\/66b218fd7040a28f7edaba70457417a2.jpeg",
      | "profile_background_tile":false,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/459807084343590912\/56kIXKWb_normal.jpeg",
      | "profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/459807084343590912\/56kIXKWb_normal.jpeg",
      | "profile_banner_url":"https:\/\/pbs.twimg.com\/profile_banners\/239995975\/1398461327",
      | "profile_link_color":"473623","profile_sidebar_border_color":"BCB302","profile_sidebar_fill_color":"171106",
      | "profile_text_color":"8A7302","profile_use_background_image":true,
      | "default_profile":false,"default_profile_image":false,"following":null,"follow_request_sent":null,
      | "notifications":null},"geo":null,"coordinates":null,"place":null,"contributors":null,"retweet_count":0,
      | "favorite_count":0,"entities":{"hashtags":[{"text":"antijuventina","indices":[45,59]}],"symbols":[],
      | "urls":[],"user_mentions":[{"screen_name":"FabRavezzani","name":"Fabio Ravezzani","id":598536444,
      | "id_str":"598536444","indices":[0,13]},{"screen_name":"RobertoRenga","name":"Roberto Renga","id":353160476,
      | "id_str":"353160476","indices":[14,27]}]},"favorited":false,"retweeted":false,"filter_level":"medium",
      | "lang":"it"}""".stripMargin

  lazy val tweet = getTweet(data)

  "The parsed tweet from json" should {
    "contain tweet id" in {
      tweet.id must beEqualTo("467968138488082432")
    }

    "contain user with id, name, screen name and number of followers" in {
      val user = tweet.user;
      user.id must beEqualTo("239995975")
      user.name must beEqualTo("cillo#32!\uf8ff")
      user.screenName must beEqualTo("cillo70")
      user.followersCount must beEqualTo(150)
    }

    "contain exactly two user mentions and they must have correct names" in {
      val mentions = tweet.userMentions;

      mentions.size must beEqualTo(2)
      mentions.filter(_.name == "Fabio Ravezzani").size must beEqualTo(1)
      mentions.filter(_.name == "Roberto Renga").size must beEqualTo(1)
    }
  }
}