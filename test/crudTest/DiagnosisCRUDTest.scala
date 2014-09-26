package crudTest

import domain.{DailyReport, Diagnosis}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.DiseaseModel.DiseaseRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo

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
      val qNA = TableQuery[QuestionAnswerRepo]
      val di = TableQuery[DiseaseRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //(diag.ddl).create
     // (qNA.ddl).create
      //(di.ddl).create
        val diaDate = DateTime.parse("2014-07-07")
        val dailyReport = DailyReport(1, "Provided Medication", Option(1), 1, 1)
        val idD = daily.returning (daily.map (_.dailyReportId) ).insert(dailyReport)

        val diagnosis = Diagnosis(1, "Flu", "Flu tablets", diaDate.toDate, Option(idD))
        val id = diag.returning (diag.map (_.diagnosisId)).insert(diagnosis)

        def Read(name: String, id : Long) = {
          diag foreach { case (diagnosis : Diagnosis) =>
            if (diagnosis.dailyReportId == Option(id)) {
              daily foreach { case (dailyReport : DailyReport) =>
                if (Option(dailyReport.dailyReportId) == diagnosis.dailyReportId) {
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
                if (Option(dailyReport.dailyReportId) == diagnosis.dailyReportId) {
                  daily.filter(_.dailyReportId === id).map(_.servicesRendered).update(name)
                }
              }
            }
          }
          Read(name, id)
        }

        def searchDelete(id: Long) : Int = {
          diag foreach { case (cr: Diagnosis) =>
            assertResult(false) {
              diag.filter(_.diagnosisId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          diag foreach { case (diagnosis : Diagnosis) =>
            if (diagnosis.diagnosisId == id) {
              diag.filter(_.diagnosisId === id).delete
              daily foreach { case (dailyReport : DailyReport) =>
                if (Option(dailyReport.dailyReportId) == diagnosis.dailyReportId) {
                  daily.filter(_.dailyReportId === diagnosis.dailyReportId).delete
                }
              }
            }
          }
          searchDelete(id)
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
