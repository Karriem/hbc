package repository

import java.util.Date

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
    def followUpDate = column[Date]("FOLLOW_UP_DATE")
    def dailyReportId = column[Option[Long]]("DAILY_REPORT_ID")
    def * = (diagnosisId, diagnosisType, treatment, followUpDate, dailyReportId) <> (Diagnosis.tupled, Diagnosis.unapply)

    val dailyReport = foreignKey("DAILYREPORT_FK", dailyReportId, TableQuery[DailyReportRepo])(_.dailyReportId)

    val diag = TableQuery[DiagnosisRepo]

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))


   /* def create = {

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").createConnection()
      (diag.ddl).create
    }*/
    /*def find(id: Long): Diagnosis = {
      val aDiagnosis = diag.filter(_.diagnosisId === id).list.head

      return aDiagnosis
    }

    def save(diagnosis: Diagnosis): Unit ={
      diag.insert(diagnosis)
    }

    /*def merge (diagnosis: Diagnosis): {

    }*/

    def remove(id: Long)= {
      diag.filter(_.diagnosisId === id).delete
    } */
  }

}