package com.github.dolphineor.bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by dolphineor on 2015-6-27.
 */
object HighLevelServerBoot {

  def resolvePath(path: String): String = path match {
    case u if u.contains("hello") => "hello"
    case u if u.contains("info") => "info"
    case _ => ""
  }

  def main(args: Array[String]) {
    implicit val system = ActorSystem("Akka-Http-Service")
    implicit val materializer = ActorMaterializer()

    val route =
      get {
        pathSingleSlash {
          complete {
            <h1>Hello, World</h1>
          }
        } ~
          path("hello") {
            extractRequest { request =>
              parameterMap { params =>
                params.foreach(pt => {
                  println(pt._1 + "->" + pt._2)
                })
                complete {
                  <h1>Hello, Akka Http</h1>
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

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() = server.flatMap(_.unbind()).onComplete(_ ⇒ system.shutdown())
    })

  }

}
