package bigpanda

import akka.actor.ActorSystem
import bigpanda.dao.EntityManagers
import bigpanda.pipeline._
import bigpanda.pipeline.api.{InputParser, InputConsumer, EventProcessor}
import bigpanda.pipeline.impl.{PandaEventProcessor, JsonInputParser, FileInputConsumer}
import bigpanda.rest.StatRoute
import spray.routing.SimpleRoutingApp

import scala.util.Try

/**
  * Created by orip on 2/26/2016.
  */
object Main extends App with SimpleRoutingApp {

  private val host: String = sys.props.getOrElse("host", "localhost")
  private val port: Int = sys.props.getOrElse("port", "8080").toInt

  private val maybePath: Option[String] = sys.props.get("gen.path")
  if (maybePath.isEmpty) {
    sys.error("error: \"gen.path\" property not given")
    sys.exit(1)
  }
  private val path: String = maybePath.get

  private val consumer: FileInputConsumer = InputConsumer.newFileOutputConsumer(path)
  private val parser: JsonInputParser = InputParser.newJsonInputParser
  private val processor: PandaEventProcessor = EventProcessor.newPandaEventProcessor

  startREST()

  Iterator.continually(consumer.getLine) foreach {
    maybeLine => {
      for {
        line <- maybeLine
        event <- Try(parser.parse(line))
      } {
        processor.process(event)
      }
    }
  }

  sys addShutdownHook {
    clean()
  }

  private def clean(): Unit = {
    consumer.close()
    EntityManagers.close()
  }

  private def startREST(): Unit = {
    implicit val system = ActorSystem("my-system")
    startServer(host, port)(StatRoute.route)
  }
}

