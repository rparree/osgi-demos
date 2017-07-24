package demo.osgi.scala

import org.osgi.framework.{BundleActivator, BundleContext}
import org.slf4j.LoggerFactory

/**
 * todo  
 */
class SampleActivator  extends BundleActivator{

  val logger= org.slf4j.LoggerFactory.getLogger(classOf[SampleActivator])

  def stop(ctx: BundleContext) ={

  }

  def start(ctx: BundleContext) = {
    logger.info("hello friend!")
  }
}
