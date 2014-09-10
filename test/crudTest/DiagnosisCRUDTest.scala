package crudTest

import domain.{DailyReport, Diagnosis}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class DiagnosisCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Diagnosis") {
    info("As a Caregiver")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val diag = TableQuery[DiagnosisRepo]
      val daily = TableQuery[DailyReportRepo]

      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //(diag.ddl ++ daily.ddl).create

        val dailyReport = DailyReport(1, "Provided Medication", 1, 1, 1)
        val idD = daily.returning (daily.map (_.dailyReportId) ).insert(dailyReport)

        val diagnosis = Diagnosis(1, "Flu", "Flu tablets", "7/07/2014", idD)
        val id = diag.returning (diag.map (_.diagnosisId) ).insert(diagnosis)

        def Read(name: String, id : Long) = {
          diag foreach { case (diagnosis : Diagnosis) =>
            if (diagnosis.dailyReportId == id) {
              daily foreach { case (dailyReport : DailyReport) =>
                if (dailyReport.dailyReportId == diagnosis.dailyReportId) {
                  assert(dailyReport.servicesRendered == name)
                }
              }
            }
          }
        }

        def Update(name:String, id:Long) = {
          diag foreach { case (diagnosis : Diagnosis) =>
            if (diagnosis.diagnosisId == id) {
              daily foreach { case (dailyReport : DailyReport) =>
                if (dailyReport.dailyReportId == diagnosis.dailyReportId) {
                  daily.filter(_.dailyReportId === id).map(_.servicesRendered).update(name)
                }
              }
            }
          }
          Read(name, id)
        }

        def Delete(id:Long) = {
          diag foreach { case (diagnosis : Diagnosis) =>
            if (diagnosis.diagnosisId == id) {
              diag.filter(_.diagnosisId === id).delete
              daily foreach { case (dailyReport : DailyReport) =>
                if (dailyReport.dailyReportId == diagnosis.dailyReportId) {
                  daily.filter(_.dailyReportId === diagnosis.dailyReportId).delete
                }
              }
            }
          }
        }

        info("Reading Diagnosis")
        Read("Provided Medication",id)
        info("Updating Diagnosis")
        Update("Provided Tablets", id)
        info("Deleting Diagnosis")
        Delete(id)
      }
    }
  }
}
