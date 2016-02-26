package bigpanda.pipeline.api

import bigpanda.datamodel.Event
import bigpanda.pipeline.impl.JsonInputParser

/**
  * Created by orip on 2/26/2016.
  */
trait InputParser {
  def parse(json: String): Event
}

object InputParser {
  def newJsonInputParser: JsonInputParser = new JsonInputParser
}
