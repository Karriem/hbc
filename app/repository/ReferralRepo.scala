package repository

import java.util.Date

import domain.Referral
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import repository.WeeklyReportModel.WeeklyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object ReferralModel {

  class ReferralRepo(tag:Tag) extends Table[Referral](tag, "REFERRAL"){

      def referralId = column[Long]("REFERRAL_ID", O.PrimaryKey, O.AutoInc)
      def referralDate = column[Date]("REFERRAL_DATE")
      def weeklyReportId = column[Option[Long]]("WEEKLY_REPORT_ID")
      def patientId = column[Long]("PATIENT_ID")
      def medicalSummaryId = column[Long]("MEDICAL_SUMMARY_ID")
      def requirements = column[String]("REQUIREMENTS")
      def coordinatorId = column[Long]("COORDINATOR_ID")
      def institutionID = column[Long]("INSTITUTION_ID")
      def * = (referralId, referralDate, weeklyReportId, patientId, medicalSummaryId, requirements, coordinatorId, institutionID) <> (Referral.tupled, Referral.unapply)

      val weeklyReport = foreignKey("WEEKLY_REPORT_FK", weeklyReportId, TableQuery[WeeklyReportRepo])(_.weeklyReportId)
      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
      val institue = foreignKey("INSTITUTION_FK", institutionID, TableQuery[InstitutionRepo])(_.instituteId)

      implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}
