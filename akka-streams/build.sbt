name := "akka-streams"

libraryDependencies ++= {
  val akkaHttpVersion = "2.0-M1"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion
  )
}
