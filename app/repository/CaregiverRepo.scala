package repository

import domain.Caregiver
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object CaregiverModel {

  class CaregiverRepo(tag:Tag) extends Table[Caregiver](tag, "CAREGIVER") {

      def caregiverId = column[Long]("CAREGIVER_ID", O.PrimaryKey, O.AutoInc)
      def patientId = column[Long]("PATIENT_ID")
      def coordinatorId = column[Long]("COORDINATOR_ID")
      def firstName = column[String]("FIRST_NAME")
      def lastName = column[String]("LAST_NAME")
      def * = (caregiverId, patientId, coordinatorId, firstName, lastName) <> (Caregiver.tupled, Caregiver.unapply)

      def patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      def coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
  }

}