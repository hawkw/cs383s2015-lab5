import sbt._
import Keys._
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin.assemblySettings
import scala.util.Properties
import Defaults.defaultSettings


object Build extends Build {
  lazy val basicSettings = Seq(
    scalaVersion := "2.11.5",
    licenses := Seq(("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))),
    scalacOptions := Seq("-encoding", "UTF-8", "-unchecked", "-deprecation", "-feature"),
    mainClass in (Compile, run) := Some("edu.allegheny.searchbot.RandomNav"),
    mainClass in assembly := Some("edu.allegheny.searchbot.RandomNav"),
    libraryDependencies += jna)

  lazy val ev3Home = Properties.envOrNone("EV3_HOME") getOrElse sys.error("$EV3_HOME environment variable is not defined")

  lazy val jna = "net.java.dev.jna" % "jna" % "3.2.7" % "provided"

  lazy val root = Project(
    "RandomNav",
    file("."),
    settings = defaultSettings ++ basicSettings ++ assemblySettings
    ).settings(
      unmanagedBase := file(ev3Home) / "lib" / "ev3",
      exportJars := true,
      jarName in assembly := "RandomNav.jar",
      excludedJars in assembly <<= unmanagedJars in Compile
      ).settings(mainClass in assembly := Some("edu.allegheny.searchbot.RandomNav"))
}