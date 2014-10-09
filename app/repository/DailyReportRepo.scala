package repository

import domain.DailyReport
import repository.CaregiverModel.CaregiverRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
import repository.WeeklyReportModel.WeeklyReportRepo

import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by tonata on 9/23/14.
 */
object DailyReportModel {

  class DailyReportRepo(tag:Tag) extends Table[DailyReport](tag, "DAILYREPORT"){

    def dailyReportId = column[Long]("DAILY_REPORT_ID", O.PrimaryKey, O.AutoInc)
    def servicesRendered = column[String]("SERVICES_RENDERED")
    def weeklyReportId = column[Option[Long]]("WEEKLY_ REPORT_ID")
    def caregiverId = column[Long]("CAREGIVER_ID")
    def patientId = column[Long]("PATIENT_ID")
    def * = (dailyReportId, servicesRendered, weeklyReportId, caregiverId, patientId) <> (DailyReport.tupled, DailyReport.unapply)

    val weeklyReport = foreignKey("WEEKLYREPORT_FK", weeklyReportId, TableQuery[WeeklyReportRepo])(_.weeklyReportId)
    val caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
    val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
  }

}
