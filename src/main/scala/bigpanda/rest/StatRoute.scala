package bigpanda.rest

import bigpanda.dao.{EventStatDao, WordStatDao}
import spray.routing.SimpleRoutingApp

object StatRoute extends SimpleRoutingApp {

  val route =
    path("stats" / "events") {
      get {
        complete {
          EventStatDao.getAll.toString()
        }
      }
    } ~
      path("stats" / "words") {
        get {
          complete {
            WordStatDao.getAll.toString()
          }
        }
      }

}