package bigpanda.rest

import bigpanda.entities.{EventStat, WordStat}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization

/**
  * Created by orip on 2/26/2016.
  */
object JsonConverter {

  implicit private val formats = Serialization.formats(NoTypeHints)

  def toJsonEventStats(eventStats: List[EventStat]):String = {
    val dictionary: Map[String, Long] = eventStats.map(e => (e.eventType, e.cnt)).toMap
    compact(render(Extraction.decompose(dictionary)))
  }

  def toJsonWordStats(wordStats: List[WordStat]):String = {
    val dictionary: Map[String, Long] = wordStats.map(e => (e.word, e.cnt)).toMap
    compact(render(Extraction.decompose(dictionary)))
  }
}
