name := "akka-http"

libraryDependencies ++= {
  val akkaVersion = "2.4.0"
  val akkaHttpVersion = "1.0"
  Seq(
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpVersion
  )
}
