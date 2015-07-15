name := "akka-http"

libraryDependencies ++= {
  val akkaVersion = "2.3.12"
  val akkaHttpVersion = "1.0-RC4"
  Seq(
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpVersion
  )
}
