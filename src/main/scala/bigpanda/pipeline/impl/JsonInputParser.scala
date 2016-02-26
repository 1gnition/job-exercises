package bigpanda.pipeline.impl

import bigpanda.datamodel.Event
import bigpanda.pipeline.api.InputParser
import org.json4s.jackson.Serialization
import org.json4s.native.JsonMethods
import org.json4s.{NoTypeHints, _}

/**
  * Created by orip on 2/26/2016.
  */
class JsonInputParser extends InputParser {
  implicit val formats = Serialization.formats(NoTypeHints)

  override def parse(json: String): Event = {
    JsonMethods.parse(json).camelizeKeys.extract[Event]
  }
}
