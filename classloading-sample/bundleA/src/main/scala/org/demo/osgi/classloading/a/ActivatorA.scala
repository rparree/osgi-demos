package org.demo.osgi.classloading.a

import org.osgi.framework.{BundleContext, BundleActivator}
import org.demo.osgi.classloading.common.Foo
import org.demo.osgi.service.SampleService

/**
 * todo
 */
class ActivatorA extends BundleActivator {
  val logger = org.slf4j.LoggerFactory.getLogger(classOf[ActivatorA])

  def stop(ctx: BundleContext) = {

  }

  def start(ctx: BundleContext) = {
    logger.info("A is starting")
    useAndPrint(Foo.incAndGetValue, "foo numbers for A")

    logger.info("Obtaining service")
    val serviceRef = ctx.getServiceReference(classOf[SampleService].getName)
    val service = ctx.getService(serviceRef).asInstanceOf[SampleService]
    useAndPrint(service.decAndGet, "service numbers for A")
    ctx.ungetService(serviceRef)
    logger.info("A released service reference")
  }

  def useAndPrint(f: () => Int, msg: String) {
    val r = (1 to 10).map(_ => f()).foldLeft(msg)((s, i) => s + "-" + i)
    logger.info(r)
  }

}
  