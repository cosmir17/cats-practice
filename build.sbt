
name := "cats-practice"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.6.0",

  "org.scalatest" %% "scalatest" % "3.0.6" % "test",
  "junit" % "junit" % "4.12" % "test"
)