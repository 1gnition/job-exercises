package bigpanda

import bigpanda.dao.{WordStatDao, EventStatDao}

/**
  * Created by orip on 2/26/2016.
  */
class EventProcessor {
  def process(event: Event): Unit = {
    event match {
      case Event(eventType, data, _) =>
        EventStatDao.inc(eventType)
        WordStatDao.inc(data)
    }
  }
}
