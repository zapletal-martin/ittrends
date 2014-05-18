package streamAnalytics.tweet

case class User(id: String, name: String, screenName: String, followersCount: Int)

case class Place(country: String, name: String) {
  override lazy val toString = s"$name, $country"
}

case class Tweet(id: String, user: User, text: String, place: Option[Place], userMentions: Set[User])
