package org.demo.osgi.service


import org.ops4j.pax.exam.junit.PaxExam
import org.junit.runner.RunWith
import javax.inject.Inject
import org.ops4j.pax.exam.{Option, Configuration}
import org.ops4j.pax.exam.CoreOptions._
import org.ops4j.pax.exam.spi.reactors.{ExamReactorStrategy, PerMethod}
import org.junit.Test
import org.junit.Assert._
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
  def config(): Array[Option] = options(
    mavenBundle().groupId("com.edc4it").artifactId("simple-service_2.11").version("0.1.0-SNAPSHOT"),
   
    mavenBundle().groupId("org.scala-lang").artifactId("scala-library").version("2.11.0"),
  
    mavenBundle( "org.apache.aries.blueprint", "org.apache.aries.blueprint", "1.0.0" ),
    mavenBundle( "org.apache.aries", "org.apache.aries.util", "1.0.0" ),
    mavenBundle( "org.apache.aries.proxy", "org.apache.aries.proxy", "1.0.0" ),
  
    junitBundles()
  )

  @Test
  def sayMyNameShouldRespondCorrectly() : Unit =  {
    assertEquals("your name is Jennifer", service.sayMyName("Jennifer"))
  }


}
