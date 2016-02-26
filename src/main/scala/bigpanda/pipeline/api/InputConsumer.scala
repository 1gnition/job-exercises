package bigpanda.pipeline.api

import bigpanda.pipeline.impl.FileInputConsumer

/**
  * Created by orip on 2/26/2016.
  */
trait InputConsumer {
  def getLine: Option[String]
  def close()
}

object InputConsumer {
  def newFileOutputConsumer(path: String): FileInputConsumer = {
    new FileInputConsumer(path)
  }
}
