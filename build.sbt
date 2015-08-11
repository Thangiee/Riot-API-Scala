name := "riot-api-scala"

version := "0.2.1"

scalaVersion := "2.11.6"

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "thangiee repo" at "http://dl.bintray.com/thangiee/maven"

libraryDependencies ++= List(
  "org.scalaj" %% "scalaj-http" % "1.1.5",
  "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
  "org.scalactic" % "scalactic_2.11" % "2.2.5",
  "com.github.thangiee" %% "play-json" % "2.4.2"
)

scalacOptions in Test ++= Seq("-Yrangepos")