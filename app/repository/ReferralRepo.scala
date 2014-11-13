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
      def instituteId = column[Long]("INSTITUTE_ID")
      def medicalSummaryId = column[Long]("MEDICAL_SUMMARY_ID")
      def requirements = column[String]("REQUIREMENTS")
      def coordinatorId = column[Long]("COORDINATOR_ID")
      def * = (referralId, referralDate, weeklyReportId, patientId, instituteId, medicalSummaryId, requirements, coordinatorId) <> (Referral.tupled, Referral.unapply)

      val weeklyReport = foreignKey("WEEKLY_REPORT_FK", weeklyReportId, TableQuery[WeeklyReportRepo])(_.weeklyReportId)
      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      val institute = foreignKey("INSTITUTE_FK", instituteId, TableQuery[InstitutionRepo])(_.instituteId)
      val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)

      implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}
