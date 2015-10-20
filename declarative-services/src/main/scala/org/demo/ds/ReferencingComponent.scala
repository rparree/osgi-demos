package org.demo.ds

import aQute.bnd.annotation.component.{Reference, Activate, Component}

/**
  * todo
  */

@Component
class ReferencingComponent {


  var d : DemoComponent =_

  @Reference
  def set(d : DemoComponent): Unit = this.d = d


  @Activate
  def init = {
    println("activating referencing component")
    d.sayMyName("Heisenberg")
  }
}
