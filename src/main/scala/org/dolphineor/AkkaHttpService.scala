package org.dolphineor

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._

import scala.io.StdIn

/**
 * Created by dolphineor on 2015-6-26.
 */
object AkkaHttpService extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  val route =
    path("hello") {
      get {
        complete {
          <h1>Say hello to akka-http</h1>
        }
      } ~
        post {
          complete {
            <h1>Say hello to akka-http</h1>
          }
        }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()

  import system.dispatcher

  // for the future transformations
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.shutdown()) // and shutdown when done
}
