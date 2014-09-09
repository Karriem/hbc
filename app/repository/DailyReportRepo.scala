package repository

import domain.DailyReport
import repository.CaregiverModel.CaregiverRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object DailyReportModel {

  class DailyReportRepo(tag:Tag) extends Table[DailyReport](tag, "DAILYREPORT"){

      def dailyReportId = column[Long]("DAILY_REPORT_ID", O.PrimaryKey, O.AutoInc)
      def servicesRendered = column[String]("SERVICES_RENDERED")
      def monthlyReportId = column[Long]("MONTHLY_ REPORT_ID")
      def caregiverId = column[Long]("CAREGIVER_ID")
      def patientId = column[Long]("PATIENT_ID")
      def * = (dailyReportId, servicesRendered, monthlyReportId, caregiverId, patientId) <> (DailyReport.tupled, DailyReport.unapply)

      def monthlyReport = foreignKey("MONTHLYREPORT_FK", monthlyReportId, TableQuery[MonthlyReportRepo])(_.monthlyReportId)
      def caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
      def patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
  }

}