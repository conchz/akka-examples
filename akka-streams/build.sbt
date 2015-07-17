name := "akka-streams"

libraryDependencies ++= {
  val akkaHttpVersion = "1.0"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion
  )
}
