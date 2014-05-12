name := "Tweet it trends"

version := "0.1"

scalaVersion := "2.10.3"

sbtVersion := "0.13.0"

resolvers ++= Seq (
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray repository" at "http://repo.spray.io",
  "Apache repo" at "https://repository.apache.org/content/repositories/staging"
)

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.3.2",
	"com.typesafe.akka" %% "akka-slf4j" % "2.3.2",
	"io.spray" % "spray-routing" % "1.3.0",
	"io.spray" % "spray-client" % "1.3.0",
	"io.spray" %% "spray-json" % "1.2.3",
	"io.spray" % "spray-testkit" % "1.3.0" % "test",
	"org.specs2" %% "specs2" % "2.3.11" % "test",
	  "org.apache.kafka" % "kafka_2.10" % "0.8.0"
	    exclude("javax.jms", "jms")
	    exclude("com.sun.jdmk", "jmxtools")
	    exclude("com.sun.jmx", "jmxri")
   	    exclude("org.slf4j", "slf4j-simple")
)