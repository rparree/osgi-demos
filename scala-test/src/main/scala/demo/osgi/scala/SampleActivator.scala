package demo.osgi.scala

import org.osgi.framework.{BundleContext, BundleActivator}

/**
 * todo  
 */
class SampleActivator  extends BundleActivator{
  def stop(ctx: BundleContext) ={

  }

  def start(ctx: BundleContext) = {
    println("hello from scala :)")
  }
}
