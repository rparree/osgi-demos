import sbt._
import Keys._

object BuildSettings {

  lazy val basicSettings = seq(
    version               := "0.1.0-SNAPSHOT",
    organization          := "com.edc4it",
    startYear             := Some(2014),
    licenses              := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion          := "2.11.0",
    resolvers             ++= Dependencies.resolutionRepos,
    scalacOptions         += "-target:jvm-1.7",
    javacOptions          ++= Seq("-source", "1.7", "-target", "1.7"),
    publishTo             := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
  )

  lazy val scalaTestSettings = basicSettings
  lazy val simpleServiceSettings = basicSettings
  lazy val simpleServiceTestSettings = basicSettings
}
