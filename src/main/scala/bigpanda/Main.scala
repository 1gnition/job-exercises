package bigpanda

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp

import scala.collection.mutable
import scala.util.Try

/**
  * Created by orip on 2/26/2016.
  */
object Main extends App with SimpleRoutingApp {

  @volatile var events: mutable.Map[String, Long] = mutable.Map.empty.withDefaultValue(0)
  @volatile var words: mutable.Map[String, Long] = mutable.Map.empty.withDefaultValue(0)

  val consumer: FileInputConsumer = InputConsumer.newFileOutputConsumer("C:\\Users\\orip\\Desktop\\generator-windows-amd64.exe")
  val parser: JsonInputParser = InputParser.newJsonInputParser
  private val processor: EventProcessor = new EventProcessor

  implicit val system = ActorSystem("my-system")

  startServer("localhost", 8080)(StatRoute.route)

  Iterator.continually(consumer.getLine).foreach(maybeLine => {
    for {
      line <- maybeLine
      event <- Try(parser.parse(line))
    } {
      processor.process(event)
    }
  })

  sys addShutdownHook {
    consumer.close()
  }

}

