package streamAnalytics.sparkStreaming

import java.util.Properties

import kafka.producer._

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.util._

class KeywordCount {

  def Run() = {
    val ssc =  new StreamingContext("local", "Tweet Analytics", Seconds(10))
    ssc.checkpoint("checkpoint")

    val lines = KafkaUtils.createStream(ssc, "localhost:2181", "group1", Map("tweets" -> 1)).map(_._2)
    val words = lines.flatMap({ f => f.split(" ")})
    val wordCounts = words.map(x => (x, 1)).reduceByKeyAndWindow(_+_, _-_, Minutes(1), Seconds(20), 2)

    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
