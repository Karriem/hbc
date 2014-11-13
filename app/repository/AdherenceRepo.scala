package repository

import domain.Adherence
import repository.PatientModel.PatientRepo
import repository.ReferralModel.ReferralRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */

object AdherenceModel {

  class AdherenceRepo(tag:Tag) extends Table[Adherence](tag, "ADHERENCE"){

      def mType = column[String]("ADHERENCE_TYPE")
      def instructions = column[String]("INSTRUCTIONS")
      def patientId = column[Long]("PATIENT_ID")
      def referralId = column[Long]("REFERRAL_ID")
      def * = (mType, instructions, patientId, referralId) <> (Adherence.tupled, Adherence.unapply)

      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      val referral = foreignKey("REFERRAL_FK", referralId, TableQuery[ReferralRepo])(_.referralId)
  }

}