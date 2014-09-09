package repository

import domain.Coordinator

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object CoordinatorModel {

  class CoordinatorRepo(tag:Tag) extends Table[Coordinator](tag, "COORDINATOR"){

      def coId = column[Long]("COORDINATOR_ID", O.PrimaryKey, O.AutoInc)
      def firstName = column[String]("FIRST_NAME")
      def lastName = column[String]("LAST_NAME")
      def * = (coId, firstName, lastName) <> (Coordinator.tupled, Coordinator.unapply)
  }

}
