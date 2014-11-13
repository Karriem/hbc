package repository

import java.util.Date

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
      def nextOfKin = column[String]("NEXT_OF_KIN")
      def nextOfKinTel = column[String]("NEXT_OF_KIN_TEL")
      def religion = column[String]("RELIGION")
      def language = column[String]("LANGUAGE")
      def carePlanDesc = column[String]("CARE_PLAN_DESC")
      def * = (patientId, dateOfContact, dateOfEvaluation, firstName, lastName, nextOfKin, nextOfKinTel, religion, language, carePlanDesc) <> (Patient.tupled, Patient.unapply)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}
