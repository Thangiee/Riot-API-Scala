name := "riot-api-scala"

version := "0.2.1"

scalaVersion := "2.11.6"

val jackson = List(
    "com.fasterxml.jackson.core" % "jackson-core",
    "com.fasterxml.jackson.core" % "jackson-annotations",
    "com.fasterxml.jackson.core" % "jackson-databind",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310"
  ).map(_ % "2.6.0")

libraryDependencies ++= jackson ++ List(
  "org.scalaj" %% "scalaj-http" % "1.1.5",
  "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
  "org.scala-lang" % "scala-reflect" % "2.11.6" ,
  "joda-time" % "joda-time" % "2.8.1" ,
  "org.joda" % "joda-convert" % "1.7" ,
  "org.scala-stm" %% "scala-stm" % "0.7",
  "org.scalactic" % "scalactic_2.11" % "2.2.5",
  "com.typesafe.play" % "play-datacommons_2.11" % "2.4.2",
  "com.typesafe.play" % "play-functional_2.11" % "2.4.2",
  "com.typesafe.play" % "play-iteratees_2.11" % "2.4.2"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

    