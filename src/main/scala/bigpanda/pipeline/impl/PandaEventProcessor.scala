package bigpanda.pipeline.impl

import bigpanda.dao.{EventStatDao, WordStatDao}
import bigpanda.datamodel.Event
import bigpanda.pipeline.api.EventProcessor

/**
  * Created by orip on 2/26/2016.
  */
class PandaEventProcessor extends EventProcessor[Event] {
  def process(event: Event): Unit = {
    event match {
      case Event(eventType, data, _) =>
        EventStatDao.inc(eventType)
        WordStatDao.inc(data)
    }
  }
}
