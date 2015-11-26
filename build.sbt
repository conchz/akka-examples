import sbt._

val logBackVersion = "1.1.3"
val slickVersion = "3.1.0"
val akkaVersion = "2.3.14"
val akkaHttpVersion = "2.0-M1"

val logBackClassic = "ch.qos.logback" % "logback-classic" % logBackVersion
val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
val logStack = Seq(logBackClassic, akkaSlf4j)

val scalaTest = "org.scalatest" %% "scalatest" % "2.2.5" % "test"
val unitTestStack = Seq(scalaTest)

val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion
val akkaStack = Seq(akkaActor, akkaCluster)

val akkaHttp = "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
val akkaHttpXml = "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpVersion
val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaHttpVersion % "test"
val akkaHttpStack = Seq(akkaHttp, akkaHttpXml, akkaHttpTestkit)

val commonDependencies = unitTestStack ++ logStack

val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.3.0"
val scalaz = "org.scalaz" %% "scalaz-core" % "7.1.5"
val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
val typesafeConfig = "com.typesafe" % "config" % "1.3.0"


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

  ivyScala := ivyScala.value map {
    _.copy(overrideScalaVersion = true)
  },

  resolvers ++= Seq(
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("releases"),
    "maven repository" at "https://repo1.maven.org/maven2/",
    "typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
    "scalaz repo" at "https://dl.bintray.com/scalaz/releases/",
    "sbt-plugin repository" at "https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
  ),

  libraryDependencies ++= commonDependencies ++ Seq(json4sJackson, scalaz, scalaXml, typesafeConfig)
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(akkaActorExamples, akkaHttpExamples, akkaStreamsExamples)

lazy val akkaActorExamples: Project = (project in file("akka-actor-examples"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= akkaStack)

lazy val akkaHttpExamples: Project = (project in file("akka-http-examples"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= akkaHttpStack)

lazy val akkaStreamsExamples: Project = (project in file("akka-streams-examples"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= akkaHttpStack)
