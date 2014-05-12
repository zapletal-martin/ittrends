package org.ittrends.tweetProxy.core

import scala.io.Source
import spray.http.HttpRequest
import org.ittrends.tweetproxy.core.OAuth

trait TwitterAuthorization {
  def authorize: HttpRequest => HttpRequest
}

trait OAuthTwitterAuthorization extends TwitterAuthorization {
  import OAuth._

  val lines = Source.fromInputStream(getClass.getResourceAsStream("/pass")).getLines().toList
  val consumer = Consumer(lines(0), lines(1))
  val token = Token(lines(2), lines(3))
  val authorize: HttpRequest => HttpRequest = oAuthAuthorizer(consumer, token)
}