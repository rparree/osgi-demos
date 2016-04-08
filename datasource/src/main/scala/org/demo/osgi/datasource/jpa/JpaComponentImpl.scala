package org.demo.osgi.datasource.jpa

import javax.persistence.EntityManager

import scala.beans.BeanProperty

/**
  * todo
  */
trait JpaComponent {

}
class JpaComponentImpl extends JpaComponent {

  val logger = org.slf4j.LoggerFactory.getLogger(classOf[JpaComponent])

  @BeanProperty
  var entityManager : EntityManager = _

  def init = {
    logger.info(s"em=${entityManager}")
  }
}
