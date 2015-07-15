name := "akka-http"

libraryDependencies ++= {
  val akkaHttpVersion = "1.0-RC4"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion
  )
}
