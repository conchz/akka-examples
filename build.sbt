name := "akka-examples"

version := "1.0"

scalaVersion := "2.11.6"

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
  "Maven Central repository" at "https://repo1.maven.org/maven2/",
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  "Sonatype repository" at "https://oss.sonatype.org/content/repositories/releases/",
  "sbt-plugin repository" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
)

libraryDependencies ++= {
  val akkaVersion = "2.3.11"
  val akkaHttpVersion = "1.0-RC2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-scala-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit-scala-experimental" % akkaHttpVersion % "test",
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
    "org.json4s" %% "json4s-jackson" % "3.2.11",
    "org.scalatest" %% "scalatest" % "2.2.5"
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