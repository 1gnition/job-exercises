package bigpanda

import spray.routing.SimpleRoutingApp

object StatRoute extends SimpleRoutingApp {

  val route = path("stat") {
    get {
      complete {
        Main.events.toString
      }
    }
  }
}