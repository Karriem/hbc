package repository

import domain.Caregiver

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object CaregiverModel {

  class CaregiverRepo(tag:Tag) extends Table[Caregiver](tag, "CAREGIVER") {

      def caregiverId = column[Long]("CAREGIVER_ID", O.PrimaryKey, O.AutoInc)
      def firstName = column[String]("FIRST_NAME")
      def lastName = column[String]("LAST_NAME")
      def * = (caregiverId, firstName, lastName) <> (Caregiver.tupled, Caregiver.unapply)

  }

}