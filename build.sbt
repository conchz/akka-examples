name := "lift-examples"

version := "1.0-snapshot"

scalaVersion := "2.11.7"

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  "maven repository" at "https://repo1.maven.org/maven2/",
  "typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  "sbt-plugin repository" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
)

libraryDependencies ++= {
  val akkaVersion = "2.3.12"
  val akkaHttpVersion = "1.0-RC4"
  Seq(
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion,
    "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
  )
}

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

unmanagedSourceDirectories in Compile <<= baseDirectory { base =>
  Seq(
    base / "src/main/scala"
  )
}

unmanagedSourceDirectories in Test <<= baseDirectory { base =>
  Seq(
    base / "src/test/scala"
  )
}