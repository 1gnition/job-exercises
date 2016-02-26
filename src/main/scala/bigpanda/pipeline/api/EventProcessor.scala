package bigpanda.pipeline.api

import bigpanda.pipeline.impl.PandaEventProcessor

/**
  * Created by orip on 2/26/2016.
  */
trait EventProcessor[T] {
  def process(event: T)
}

object EventProcessor {
  def newPandaEventProcessor: PandaEventProcessor = new PandaEventProcessor
}

