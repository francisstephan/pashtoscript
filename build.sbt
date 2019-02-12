ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

scalaJSUseMainModuleInitializer := true
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
// useYarn := true
webpackBundlingMode := BundlingMode.Application

lazy val root = (project in file("."))
  .settings(
    name := "pashto",
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6",
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "1.3.1",
    skip in packageJSDependencies := false,
    npmDependencies in Compile ++= Seq(
       "react" -> "16.5.1",
       "react-dom" -> "16.5.1"
    )
  )
