lazy val commonSettings = Seq(
  name := "akka-examples",
  version := "0.1.0-SNAPSHOT",
  organization := "com.github.dolphineor",

  scalaVersion := "2.11.7",

  scalacOptions ++= Seq(
    "-encoding", "UTF-8",
    "-unchecked"
  ),

  javacOptions ++= Seq(
    "-source", "1.8",
    "-target", "1.8",
    "-Xlint:unchecked"
  ),

  resolvers ++= Seq(
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("releases"),
    "maven repository" at "https://repo1.maven.org/maven2/",
    "typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
    "sbt-plugin repository" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
  ),

  libraryDependencies ++= {
    val akkaVersion = "2.4.0"
    Seq(
      "com.typesafe" % "config" % "1.3.0",
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
      "org.scalaz" %% "scalaz-core" % "7.1.5",
      "org.scalatest" %% "scalatest" % "2.2.5" % "test"
    )
  },

  ivyScala := ivyScala.value map {
    _.copy(overrideScalaVersion = true)
  },

  unmanagedSourceDirectories in Compile <<= baseDirectory { base =>
    Seq(
      base / "src/main/scala"
    )
  },
  unmanagedSourceDirectories in Test <<= baseDirectory { base =>
    Seq(
      base / "src/test/scala"
    )
  }
)


lazy val akkaActor = (project in file("akka-actor")).
  settings(commonSettings: _*)

lazy val akkaHttp = (project in file("akka-http")).
  settings(commonSettings: _*).
  settings(
    // other settings
  )

lazy val akkaStreams = (project in file("akka-streams")).
  settings(commonSettings: _*)
