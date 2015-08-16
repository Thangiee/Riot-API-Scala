name := "riot-api-scala"

version := "0.2.1"

scalaVersion := "2.11.6"

organization := "com.github.thangiee"

publishMavenStyle := true

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "thangiee repo" at "http://dl.bintray.com/thangiee/maven"

libraryDependencies ++= List(
  "org.scalaj" %% "scalaj-http" % "1.1.5",
  "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
  "org.scalactic" % "scalactic_2.11" % "2.2.5",
  "com.github.thangiee" %% "play-json" % "2.4.2",
  "com.github.cb372" %% "scalacache-guava" % "0.6.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)

scalacOptions in Test ++= Seq("-Yrangepos")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayVcsUrl := Some("https://github.com/Thangiee/Riot-API-Scala")
