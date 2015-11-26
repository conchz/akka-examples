package com.github.dolphineor.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created on 2015-06-27.
 *
 * @author dolphineor
 */
object HighLevelServerBootstrap {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Akka-Http-Service")
    implicit val materializer = ActorMaterializer()

    val route =
      get {
        pathSingleSlash {
          complete {
            HttpResponse(entity = HttpEntity(MediaTypes.`text/html`,
              "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<h1>Hello World</h1>" +
                "</body>" +
                "</html>")
            )
          }
        } ~
          path("hello") {
            extractRequest { request =>
              parameterMap { params =>
                params.foreach(pt => {
                  println(pt._1 + "->" + pt._2)
                })
                complete {
                  HttpResponse(entity = HttpEntity(MediaTypes.`text/html`,
                    "<!DOCTYPE html>" +
                      "<html>" +
                      "<head>" +
                      "<meta charset=\"UTF-8\">" +
                      "</head>" +
                      "<body>" +
                      "<h1>Hello, Akka Http</h1>" +
                      "</body>" +
                      "</html>")
                  )
                }
              }
            }
          } ~
          path("info") {
            complete("Info: " + system.log)
          }
      } ~
        post {
          path(RestPath) { path =>
            complete(path.toString())
          }
        }


    val server = Http().bindAndHandle(route, "::0", 8080)

    sys.addShutdownHook(server.flatMap(_.unbind()).onComplete(_ ⇒ system.shutdown()))
  }

}
