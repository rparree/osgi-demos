import sbt._
import Keys._

object BuildSettings {

  lazy val basicSettings = seq(
    version                 := "0.1.0-SNAPSHOT",
    organization            := "com.edc4it",
    startYear               := Some(2014),
    licenses                := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion            := "2.11.5",
    crossPaths              := false,
    resolvers               ++= Dependencies.resolutionRepos,
    scalacOptions           ++= Seq("-target:jvm-1.7", "-deprecation"),
    javacOptions in compile ++= Seq("-source", "1.7", "-target", "1.7"),
    javacOptions in doc     ++= Seq("-source", "1.7"),
    publishMavenStyle       := true,
    publishTo               := Some("Sonatype Snapshots Nexus" at "http://localhost:8081/repository/snapshots/"),
    credentials             += Credentials("Sonatype Nexus Repository Manager", "localhost", "admin", "admin123"),
    shellPrompt in ThisBuild:= { state => Project.extract(state).currentRef.project + "> " }
  )

  lazy val scalaTestSettings = basicSettings
  lazy val simpleServiceSettings = basicSettings
  lazy val simpleServiceTestSettings = basicSettings
}
