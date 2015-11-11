name := "akka-http"

libraryDependencies ++= {
  val akkaHttpVersion = "2.0-M1"
  Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpVersion
  )
}
