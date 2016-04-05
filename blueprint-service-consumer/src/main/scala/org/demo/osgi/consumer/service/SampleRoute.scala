package org.demo.osgi.consumer.service

import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo
 */
class SampleRoute extends RouteBuilder {
  //routeId "service consumer demo" 
  "jetty:http://localhost:9191/service" ==> {
    log("received")
    -->("bean:consumer?method=updateMessage")
  }
}
