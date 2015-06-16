import com.typesafe.sbt.osgi.OsgiKeys
import com.typesafe.sbt.osgi.SbtOsgi._
import sbt.Keys._
import sbt._

object OsgiDemosBuild extends Build {

  import BuildSettings._
  import Dependencies._

  val resolutionRepos = Seq(
    "Twitter Maven Repo" at "http://maven.twttr.com/",
    "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  )

  lazy val parent = Project(id = "osgi-demos",
    base = file("."))
    .aggregate(scalaTest, simpleService, simpleServiceTest, common, bundleA, bundleB)
    .settings(basicSettings: _*)

  lazy val scalaTest = Project(id = "scala-test", base = file("scala-test"))
    .settings(scalaTestSettings: _*)
    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Seq("demo.osgi.scala.*"),
      OsgiKeys.bundleActivator := Some("demo.osgi.scala.SampleActivator")
    )

    .settings(libraryDependencies ++= compile(osgi))

  lazy val simpleServiceTest = Project(id = "simple-service-test", base = file("simple-service-test"))
    .settings(simpleServiceTestSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j)
    ++ test(slf4jLog4j, paxExam, inject, paxExamCDI,
    paxAether, mvnUrl, guava, examNative, felix))
    .dependsOn(simpleService)

  lazy val simpleService = Project(id = "simple-service", base = file("simple-service"))
    .settings(basicSettings: _*)
    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Seq("org.demo.osgi.service.impl.*"),
      OsgiKeys.exportPackage := Seq("org.demo.osgi.service")
    )
    .settings(libraryDependencies ++= compile(osgi, slf4j) )

  lazy val common = Project(id = "common", base = file("classloading-sample/common"))
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.exportPackage := Seq("!*blah","org.demo.osgi.classloading.common.*")
    )

  lazy val bundleA = Project(id = "bundleA", base = file("classloading-sample/bundleA"))
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.exportPackage := Seq("org.demo.osgi.classloading.a"),
      OsgiKeys.bundleActivator := Option("org.demo.osgi.classloading.a.ActivatorA")
    )
    .dependsOn(common, simpleService)


  lazy val bundleB = Project(id = "bundleB", base = file("classloading-sample/bundleB"))
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.exportPackage := Seq("org.demo.osgi.classloading.b"),
      OsgiKeys.bundleActivator := Option("org.demo.osgi.classloading.b.ActivatorB")
    )
    .dependsOn(common, simpleService)


  lazy val blueprintCamelDemo = Project(id = "camel-blueprint", base = file("camel-blueprint"))
    .settings(basicSettings: _*)
    .settings(
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelSeq : _*)
        ++ test (camelTest, slf4jLog4j)
    )

    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Seq("demo.blueprint.routes"),
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","*")
    )
    .dependsOn(simpleService)


  lazy val fabricJetty = Project(id = "fabric-jetty", base = file("fabric-demo/fabric-jetty"))
    .settings(basicSettings: _*)
    .settings(
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelSeq : _*)
        ++ test (camelTest, slf4jLog4j)
    )

    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Nil,
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","io.fabric8.camel")
    )

  lazy val fabricDemoClientClient = Project(id = "fabric-demo-client", base = file("fabric-demo/fabric-demo-client"))
    .settings(basicSettings: _*)
    .settings(
      
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelSeq : _*)
        ++ test (camelTest, slf4jLog4j)
    )

    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Nil,
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","io.fabric8.camel")
    )



}
