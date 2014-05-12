name := "Tweet it trends"

version := "0.1"

scalaVersion := "2.10.4"

sbtVersion := "0.13.0"

resolvers ++= Seq (
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray repository" at "http://repo.spray.io",
  "Apache repo" at "https://repository.apache.org/content/repositories/staging"
)

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-streaming_2.10" % "0.9.0-incubating",
  "org.apache.spark" % "spark-streaming-kafka_2.10" % "0.9.0-incubating"
)