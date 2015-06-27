package org.dolphineor.bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by dolphineor on 2015-6-27.
 */
object AkkaHttpServerBoot {
  def main(args: Array[String]) {
    // the actor system to use. Required for actorMaterializer and HTTP.
    // passed in implicit
    implicit val system = ActorSystem("Akka-Http-Service")
    implicit val materializer = ActorMaterializer()

    val server = Http().bindAndHandleAsync(handler = asyncHandler, interface = "localhost", port = 8080)
    println("Server start finished...")

    Runtime.getRuntime.addShutdownHook(new Thread() {
      // trigger unbinding from the port and shutdown when done.
      override def run() = server.flatMap(_.unbind()).onComplete(_ ⇒ system.shutdown())
    })

  }

  // With an async handler, we use futures. Threads aren't blocked.
  def asyncHandler(request: HttpRequest): Future[HttpResponse] = {

    // we match the request, and some simple path checking
    request match {
      // match specific path
      case HttpRequest(GET, Uri.Path("/list"), _, _, _) => {
        Future(HttpResponse(status = StatusCodes.OK))
      }

      // match GET pat. Return a single ticker
      case HttpRequest(GET, Uri.Path("/get"), _, _, _) => {
        // next we match on the query paramter
        val paramMap = request.uri.query.toMap
        request.uri.query.get("t") match {

          // if we find the query parameter
          case Some(queryParameter) => {
            // specific operation
            paramMap.foreach(pt => print(pt._1 + ": " + pt._2 + "\n"))
            Future(HttpResponse(entity = "Akka Http"))
          }
          // if the query parameter isn't there
          case None => Future(HttpResponse(status = StatusCodes.OK))
        }
      }
      // Simple case that matches everything, just return a not found
      case HttpRequest(_, _, _, _, _) => {
        Future[HttpResponse] {
          HttpResponse(status = StatusCodes.NotFound)
        }
      }
    }
  }

}
