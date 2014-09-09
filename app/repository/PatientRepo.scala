package repository

import java.sql.Date

import domain.Patient

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object PatientModel {

  class PatientRepo(tag:Tag) extends Table[Patient](tag, "PATIENT"){

      def patientId = column[Long]("PATIENT_ID", O.PrimaryKey, O.AutoInc)
      def dateOfContact = column[Date]("DATE_OF_CONTACT")
      def dateOfEvaluation = column[Date]("DATE_OF_EVALUATION")
      def firstName = column[String]("FIRST_NAME")
      def lastName = column[String]("LAST_NAME")
      def * = (patientId, dateOfContact, dateOfEvaluation, firstName, lastName) <> (Patient.tupled, Patient.unapply)

  }

}
