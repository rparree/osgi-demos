package org.demo.ds

//import org.osgi.service.component.annotations.{Activate, Component} // OSGI cmpn 5
//import org.apache.felix.scr.annotations.{Activate, Component} // SCR
import java.util.Properties

import aQute.bnd.annotation.component.{ConfigurationPolicy, Activate, Component}

/**
 * todo
 */

@Component(
  name = "DemoComponent",
  configurationPolicy = ConfigurationPolicy.optional
)
class DemoComponentImpl extends DemoComponent {


  override def sayMyName(s: String): Unit = println(s"your name is $s")

  @Activate
  def init(properties: java.util.Map[String,String]) ={
    println(s"activated with properties $properties")
  }

}

trait DemoComponent {
  def sayMyName(s : String)
}
