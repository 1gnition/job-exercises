package bigpanda

/**
  * Created by orip on 2/26/2016.
  */
class EventProcessor {
  def process(event: Event): Unit = {
    event match {
      case Event(eventType, data, _) =>
        Main.events(eventType) += 1
        Main.words(data) += 1
    }
  }
}
