package crudTest

import domain.{DailyReport, Diagnosis, Disease}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.DiseaseModel.DiseaseRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class DiseaseCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Patient") {
    info("As a Caregiver")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val dis = TableQuery[DiseaseRepo]
      val diag = TableQuery[DiagnosisRepo]
      val daily = TableQuery[DailyReportRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


        info("Creating Disease")
        val diaDate = DateTime.parse("2014-08-07")

        val dailyReoprt = DailyReport(1, "Provided Medication", Option(1), 1, 1)
        val idDialy = daily.returning (daily.map (_.dailyReportId) ).insert(dailyReoprt)

        val diagnosis = Diagnosis(1, "TB", "Tablets", diaDate.toDate, Option(idDialy), "Workplace Routine")
        val idDiag = diag.returning (diag.map (_.diagnosisId)).insert(diagnosis)

        val disease = Disease(1, "TB", "Coughing", idDiag)
        val idDis = dis.returning (dis.map (_.diseaseId) ).insert(disease)

        def Read(name: String, id : Long) = {
          dis foreach { case (disease: Disease) =>
            if (disease.diseaseId == id) {
              diag foreach { case (diagnosis: Diagnosis) =>
                if (diagnosis.diagnosisId == disease.diagnosisId) {
                  assert(diagnosis.treatment == name)
                }
              }
            }
          }
        }

        def Update(name:String, id:Long) = {
          dis.filter(_.diseaseId === id).map(_.symptoms).update(name)

          dis foreach { case (disease: Disease) => {
            if (disease.diseaseId == id) {
              assert(disease.symptoms == name)
            }
          }
          }
        }

        def searchDelete(id: Long) : Int = {
          dis foreach { case (cr: Disease) =>
            assertResult(false) {
              dis.filter(_.diseaseId === id).exists.run
            }
          }

          return 0;
        }

        def Delete(id:Long) = {
          dis foreach { case (disease: Disease) => {
            if (disease.diseaseId == id) {
              dis.filter(_.diseaseId === id).delete
              diag foreach { case (diagnosis: Diagnosis) => {
                if (diagnosis.diagnosisId == disease.diagnosisId) {
                  diag.filter(_.diagnosisId === idDiag).delete
                }
              }
            }
              //dis.filter(_.diseaseId === id).delete

            }
          }
          }
          searchDelete(id)
        }

        info("Reading Disease")
        Read("Tablets", idDis)
        info("Updating Disease")
        Update("Puking", idDis)
        info("Deleting Disease")
        Delete(idDis)
      }
    }
  }
}
