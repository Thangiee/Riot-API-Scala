name := "riot-api-scala"

version := "0.2.1"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-json_2.11" % "2.4.0-M2",
  "org.scalaj" %% "scalaj-http" % "1.1.0",
  "org.specs2" %% "specs2-core" % "2.4.14" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.14" % "test",
  "org.mockito" % "mockito-all" % "1.10.8"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

    