package org.demo.osgi.classloading.b

import org.osgi.framework.{BundleContext, BundleActivator}
import org.demo.osgi.classloading.common.Foo
import org.demo.osgi.service.SampleService

/**
 * todo  
 */
class ActivatorB extends BundleActivator {
  val logger = org.slf4j.LoggerFactory.getLogger(classOf[ActivatorB])

  def stop(ctx: BundleContext) = {

  }

  def start(ctx: BundleContext) = {
    logger.info("B is starting")
    useAndPrint(Foo.incAndGetValue, "foo numbers for B")

    logger.info("Obtaining SampleService")
    val serviceRef = Option(ctx.getServiceReference(classOf[SampleService].getName))
    serviceRef match {
      case Some(ref) =>
        val service = ctx.getService(ref).asInstanceOf[SampleService]
        useAndPrint(service.decAndGet, "service numbers for B")
        ctx.ungetService(ref)
        logger.info("B released SampleService reference")
      case None => logger.error("SampleService not found")
    }
  }


  def useAndPrint(f: () => Int, msg: String) {
    val r = (1 to 10).map(_ => f()).foldLeft(msg)((s, i) => s + "-" + i)
    logger.info(r)
  }

}
