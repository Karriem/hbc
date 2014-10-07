package repository

import domain.Adherence
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */

object AdherenceModel {

  class AdherenceRepo(tag:Tag) extends Table[Adherence](tag, "ADHERENCE"){

      def mType = column[String]("ADHERENCE_TYPE")
      def instructions = column[String]("INSTRUCTIONS")
      def patientId = column[Long]("PATIENT_ID")
      def * = (mType, instructions, patientId) <> (Adherence.tupled, Adherence.unapply)

      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
  }

}