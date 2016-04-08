import sbt._

object Dependencies {

  val resolutionRepos = Seq(
    "JBoss releases" at "https://repository.jboss.org/nexus/content/repositories/releases/",
    "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  )

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")

  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  val osgi = "org.osgi" % "org.osgi.core" % "4.3.1"
  val osgiCompendium = "org.osgi" % "org.osgi.compendium" % "4.3.0"
  val scrAnnotations = "org.apache.felix" % "org.apache.felix.scr.annotations" % "1.9.12"
  val bndlib = "biz.aQute.bnd" % "bndlib" % "2.4.0"
  var slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"


  // ---
  var karafFeatures = "org.apache.karaf.assemblies.features" % "standard" % "2.4.0"
  var slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.7.7"
  var paxExamContainerKaraf  ="org.ops4j.pax.exam" % "pax-exam-container-karaf" % "4.8.0"
  var paxExamJunit = "org.ops4j.pax.exam" % "pax-exam-junit4" % "4.8.0"
  var paxExam = "org.ops4j.pax.exam" % "pax-exam" % "4.8.0"
  var paxUrlAether = "org.ops4j.pax.url" % "pax-url-aether" % "2.4.2"
  var inject = "javax.inject" % "javax.inject" % "1"
  // ---

  var camelSeq = Seq("camel-core", "camel-jetty","camel-scala") map ("org.apache.camel" % _ % "2.15.0")
  var junitInterface = "com.novocode" % "junit-interface" % "0.11"

  var camelTest = "org.apache.camel" % "camel-test-blueprint" % "2.15.0"
  var arquillianContainer = "org.jboss.arquillian.container" % "arquillian-container-karaf-managed" % "2.1.0.CR15"
  var arquillianTest = "org.jboss.arquillian.junit" % "arquillian-junit-container" % "1.1.4.Final"

  var hibernate = "org.hibernate" % "hibernate-entitymanager" % "4.2.21.Final"

}
