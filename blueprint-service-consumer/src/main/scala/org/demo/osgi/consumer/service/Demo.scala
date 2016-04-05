package org.demo.osgi.consumer.service

import org.demo.osgi.service.SampleService

import scala.beans.BeanProperty

/**
 * todo
 */
class Demo {
  
  @BeanProperty
  var service : SampleService = _

  def updateMessage(s: String ) : String = service.sayMyName("No, 'Everybody Wins'")
}
