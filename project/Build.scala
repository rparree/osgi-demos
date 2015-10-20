
import com.typesafe.sbt.osgi.SbtOsgi
import com.typesafe.sbt.osgi.SbtOsgi.autoImport._
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
    .enablePlugins(SbtOsgi)
    .aggregate(scalaTest, simpleService, simpleServiceTest, common, bundleA, bundleB)
    .settings(basicSettings: _*)

  lazy val scalaTest = Project(id = "scala-test", base = file("scala-test"))
    .enablePlugins(SbtOsgi)
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

  lazy val simpleServiceConsumer = Project(id = "simple-service-consumer", base = file("simple-service-consumer"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelSeq : _*)
        ++ test (camelTest, slf4jLog4j)
    )


    .settings(
      OsgiKeys.privatePackage := Seq("org.demo.osgi.consumer.service"),
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","*")
    )
    .dependsOn(simpleService)

    


  lazy val simpleService = Project(id = "simple-service", base = file("simple-service"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Seq("org.demo.osgi.service.impl.*"),
      OsgiKeys.exportPackage := Seq("org.demo.osgi.service")
    )
    .settings(libraryDependencies ++= compile(osgi, slf4j) )

  lazy val common = Project(id = "common", base = file("classloading-sample/common"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(
      OsgiKeys.exportPackage := Seq("!*blah","org.demo.osgi.classloading.common.*")
    )

  lazy val bundleA = Project(id = "bundleA", base = file("classloading-sample/bundleA"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(
      OsgiKeys.exportPackage := Seq("org.demo.osgi.classloading.a"),
      OsgiKeys.bundleActivator := Option("org.demo.osgi.classloading.a.ActivatorA")
    )
    .dependsOn(common, simpleService)


  lazy val bundleB = Project(id = "bundleB", base = file("classloading-sample/bundleB"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile(osgi, slf4j))
    .settings(
      OsgiKeys.exportPackage := Seq("org.demo.osgi.classloading.b"),
      OsgiKeys.bundleActivator := Option("org.demo.osgi.classloading.b.ActivatorB")
    )
    .dependsOn(common, simpleService)


  lazy val blueprintCamelDemo = Project(id = "camel-blueprint", base = file("camel-blueprint"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelSeq : _*)
        ++ test (camelTest, slf4jLog4j)
    )

    .settings(
      OsgiKeys.privatePackage := Seq("demo.blueprint.routes"),
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","*")
    )
    .dependsOn(simpleService)




  lazy val declarativeServices = Project(id = "declarative-services", base = file("declarative-services"))
    .enablePlugins(SbtOsgi)
    .settings(osgiSettings: _*)
    .settings(basicSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Seq("org.demo.ds.*"),
      OsgiKeys.additionalHeaders := Map(
        "Service-Component" -> "*", // bnd annotations
      "_dsannotations" -> "*" // OSGi standard annotations

      )
    
    )
    .settings(libraryDependencies ++= compile(osgi, bndlib))



}
