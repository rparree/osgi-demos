package org.demo.osgi.classloading.common

import java.util.concurrent.atomic.AtomicInteger

/**
 * todo
 */
object ScalaFoo {
  val i = new AtomicInteger

  def incAndGetValue = i.incrementAndGet
  
}
