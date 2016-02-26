package bigpanda.rest

import bigpanda.dao.{EventStatDao, WordStatDao}
import spray.routing.SimpleRoutingApp

object StatRoute extends SimpleRoutingApp {

  val route =
    path("stats" / "events") {
      get {
        complete {
          JsonConverter.toJsonEventStats(EventStatDao.getAll)
        }
      }
    } ~
      path("stats" / "words") {
        get {
          complete {
            JsonConverter.toJsonWordStats(WordStatDao.getAll)
          }
        }
      }

}