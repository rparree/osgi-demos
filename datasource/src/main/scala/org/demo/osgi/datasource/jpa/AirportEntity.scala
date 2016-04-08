package org.demo.osgi.datasource.jpa

;

import java.io.Serializable
import java.math.BigDecimal

import scala.beans.BeanProperty;

/**
  * todo
  */
class AirportEntity {

  @BeanProperty
  var airportid: Int = 0
  @BeanProperty
  var airportname: String = _
  @BeanProperty
  var airportcode: String = _

}
