package repository

import domain.Medication
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */

object MedicationModel {

  class MedicationRepo(tag:Tag) extends Table[Medication](tag, "MEDICATION"){

      def mType = column[String]("MEDICATION_TYPE")
      def instructions = column[String]("INSTRUCTIONS")
      def patientId = column[Long]("PATIENT_ID")
      def * = (mType, instructions, patientId) <> (Medication.tupled, Medication.unapply)

      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
  }

}