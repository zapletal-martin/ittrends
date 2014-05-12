package tweetProxy.core.query

import spray.http._
import spray.client.pipelining._
import spray.http.HttpRequest
import org.ittrends.tweetProxy.core.TwitterAuthorization
import org.ittrends.tweetproxy.core.TweetReaderActor
import akka.io.IO
import spray.can.Http

trait TwitterStreamApiRequest extends  {
  this: TwitterAuthorization with TweetReaderActor =>

  val io = IO(Http)(context.system)

  def twitterStreamingApiRequest(uri: Uri, keywords: Set[String], following: Set[String]) = {
    def post = {
      def createUrlParameter (paramName: String, values: Set[String]) =
        if (values.isEmpty) "" else s"$paramName=${values.mkString(",")}"
      def mergeUrlParameters(params: String*) =
        params.filter(!_.isEmpty).mkString("&")

      val kw = createUrlParameter("track", keywords)
      val flw = createUrlParameter("follow", following)

      val res = mergeUrlParameters(kw, flw)

      HttpEntity(ContentType(MediaTypes.`application/x-www-form-urlencoded`), res)
    }

    sendTo(io).withResponsesReceivedBy(self)(HttpRequest(HttpMethods.POST, uri = uri, entity = post) ~> authorize)
  }
}
