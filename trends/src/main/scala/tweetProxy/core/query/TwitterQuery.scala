package tweetProxy.core.query

import tweetProxy.core.CsvFileLoader

trait TwitterQuery {
  val keywords: Set[String]
  val following: Set[String]
}

trait ProgrammingTwitterQuery extends TwitterQuery with CsvFileLoader {
  lazy val keywords = loadWords("/keywords.csv")
  lazy val following = loadWords("/following.csv")
}
