package org.demo.osgi.service


import java.io.File

import org.ops4j.pax.exam.junit.PaxExam
import org.junit.runner.RunWith
import javax.inject.Inject

import org.ops4j.pax.exam.{Configuration, ConfigurationManager, Option}
import org.ops4j.pax.exam.CoreOptions._
import org.ops4j.pax.exam.spi.reactors.{ExamReactorStrategy, PerMethod}
import org.junit.Test
import org.junit.Assert._
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption._
import org.ops4j.pax.exam.options.{MavenArtifactUrlReference, MavenUrlReference}
;

/**
  * todo
  */
@RunWith(classOf[PaxExam])
@ExamReactorStrategy(Array(classOf[PerMethod]))
class ServiceTest {

  @Inject
  var service: SampleService = _

  @Configuration
  def config(): Array[Option] = {
    val karafUrl: MavenArtifactUrlReference = maven
      .groupId("org.apache.karaf")
      .artifactId("apache-karaf")
      .version(karafVersion)
      .`type`("zip")
    val karafStandardRepo: MavenUrlReference = maven
      .groupId("org.apache.karaf.assemblies.features")
      .artifactId("standard")
      .version(karafVersion)
      .classifier("features")
      .`type`("xml")
    val karafCamelRepo: MavenUrlReference = maven
      .groupId("org.apache.camel.karaf")
      .artifactId("apache-camel")
      .version("2.15.1")
      .classifier("features")
      .`type`("xml")

    options(
      repository("http://localhost:8081/content/groups/public/").allowSnapshots(),

      karafDistributionConfiguration
        .frameworkUrl(karafUrl)
        .karafVersion("2.4.0")

        .unpackDirectory(new File("target", "exam"))
        .useDeployFolder(false),

      configureConsole.ignoreLocalConsole,
      features(karafStandardRepo, "scr"),
      features(karafCamelRepo, "camel-scala"),
      mavenBundle() // make sure to publish locally in ~/.m2
        .groupId("com.edc4it")
        .artifactId("blueprint-service")
        .version("0.1.0-SNAPSHOT")
        .start
    )
  }

  @Test
  def sayMyNameShouldRespondCorrectly(): Unit = {
    assertEquals("your name is Heisenberg", service.sayMyName("Heisenberg"))
  }

  def karafVersion: String = {
    val cm: ConfigurationManager = new ConfigurationManager
    val karafVersion: String = cm.getProperty("pax.exam.karaf.version", "2.4.0")
    return karafVersion
  }

}
