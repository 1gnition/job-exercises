package bigpanda.pipeline.impl

import java.io.{BufferedReader, InputStream, InputStreamReader}

import bigpanda.pipeline.api.InputConsumer

import scala.util.{Failure, Success, Try}

/**
  * Created by orip on 2/26/2016.
  */
class FileInputConsumer(path: String) extends InputConsumer {
  private val processBuilder = new ProcessBuilder(path)
  private val process: Process = processBuilder.start()
  private val stream: InputStream = process.getInputStream
  private val reader = new BufferedReader(new InputStreamReader(stream))

  override def getLine: Option[String] = {
    val maybeLine: Try[String] = Try(reader.readLine())
    maybeLine match {
      case Failure(ex) => None
      case Success(line) if line != null => Some(line)
      case Success(line) if line == null => None
    }
  }

  override def close(): Unit = {
    reader.close()
    stream.close()
    process.destroy()
  }
}
