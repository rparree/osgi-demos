package org.demo.osgi.service.impl

import org.demo.osgi.service.SampleService
import java.util.concurrent.atomic.AtomicInteger

/**
 * todo
 */
class SampleServiceImpl extends SampleService {
  override def sayMyName(name: String) = s"your name is $name"

  private val i = new AtomicInteger

  override def decAndGet() = i.decrementAndGet()  
}
