package bigpanda

import scala.util.Try

/**
  * Created by orip on 2/26/2016.
  */
object App {
  def main(args: Array[String]) {

    val consumer: FileInputConsumer = InputConsumer.newFileOutputConsumer("C:\\Users\\orip\\Desktop\\generator-windows-amd64.exe")
    val parser: JsonInputParser = InputParser.newJsonInputParser

    println(Try(parser.parse(consumer.getLine)).getOrElse("error"))
    println(Try(parser.parse(consumer.getLine)).getOrElse("error"))
    println(Try(parser.parse(consumer.getLine)).getOrElse("error"))
    println(Try(parser.parse(consumer.getLine)).getOrElse("error"))
    println(Try(parser.parse(consumer.getLine)).getOrElse("error"))

    consumer.close()
  }

}
