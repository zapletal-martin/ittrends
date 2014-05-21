import sbt.Keys._

name := "Tweet it trends"

version := "0.1"

scalaVersion := "2.10.4"

sbtVersion := "0.13.0"

resolvers ++= Seq (
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray repository" at "http://repo.spray.io",
  "Apache repo" at "https://repository.apache.org/content/repositories/staging",
  "repo.codahale.com" at "http://repo.codahale.com"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-streaming_2.10" % "0.9.0-incubating",
  "org.apache.spark" % "spark-streaming-kafka_2.10" % "0.9.0-incubating",
  "org.specs2" %% "specs2" % "2.3.12" % "test",
  "com.typesafe.play" %% "play-json" % "2.2.1"
)

scalacOptions in Test ++= Seq("-Yrangepos")


