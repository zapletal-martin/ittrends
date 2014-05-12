package tweetProxy.core

import scala.io.Source

trait CsvFileLoader {
  def loadWords(fileName: String): Set[String] = {
      Source.fromInputStream(getClass.getResourceAsStream(fileName)).getLines().toSeq match {
        case Nil => Set()
        case _@list => list.flatMap(line => line.split(",")).toSet
    }
  }
}
