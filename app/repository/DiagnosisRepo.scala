package repository

import domain.Diagnosis
import repository.DailyReportModel.DailyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object DiagnosisModel {

  class DiagnosisRepo(tag:Tag) extends Table[Diagnosis](tag, "DIAGNOSIS"){

    def diagnosisId = column[Long]("DIAGNOSIS_ID", O.PrimaryKey, O.AutoInc)
    def diagnosisType = column[String]("DIAGNOSIS_TYPE")
    def treatment = column[String]("TREATMENT_DESCRIPTION")
    def followUpDate = column[String]("FOLLOW_UP_DATE")
    def dailyReportId = column[Long]("DAILY_REPORT_ID")
    def * = (diagnosisId, diagnosisType, treatment, followUpDate, dailyReportId) <> (Diagnosis.tupled, Diagnosis.unapply)

    val dailyReport = foreignKey("DAILYREPORT_FK", dailyReportId, TableQuery[DailyReportRepo])(_.dailyReportId)
  }

}