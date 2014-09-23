package services

import domain.{Diagnosis, Disease, QuestionAnswer}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.DiagnosisModel.DiagnosisRepo
import repository.DiseaseModel.DiseaseRepo
import services.impl.DiagnosisServiceImpl

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/19/14.
 */
class DiagnosisServiceTest extends FeatureSpec with GivenWhenThen{

  feature("Diagnosis Service"){
    info("I want to carry out specific diagnosis services")

    scenario("Creating object instances"){
      Given("Specific entity information")

      val diagnosis = Diagnosis(1, "Asthmatic", "Antibiotics", "7/07/2014", None)

      val qAndA = QuestionAnswer("How long has the coughing persisted", None, 1L)

      val disease = Disease(1L, "Asthma", "Excessive Coughing", 1L)

      val diagnosisRepo = TableQuery[DiagnosisRepo]
      val diseasesRepo = TableQuery[DiseaseRepo]
      var id : Long = 0L
      val diaService : DiagnosisService = new DiagnosisServiceImpl()

      def testCreateDiagnosis = {
        id = diaService.createDiagnosis(diagnosis, disease, qAndA)

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
          diagnosisRepo foreach { case (d: Diagnosis) =>
            if(d.followUpDate == "7/07/2014"){

              diseasesRepo foreach { case (disease: Disease) =>
                if(disease.diagnosisId == id){
                  assert(disease.diseaseType == "Asthma")  // Test for disease within diagnosis
                }
              }
            }
          }
        }

      }

      def testGetDisease = {
        val disease = diaService.getDisease(id)
        assert(disease.diseaseType == "Asthma")
      }

      def testGetDiagnosis = {
        val diagnosis = diaService.getDiagnosis(id)
        assert(diagnosis.diagnosisType == "Asthmatic")
      }

      // Needs Reviewing , possibly a DailyReport Service
      def testGetAllDiagnosisByCaregiver{
        //diaService.getAllDiagnosisByCaregiver()
      }

      info("Creating Diagnosis")
      testCreateDiagnosis
      info("Retrieving Diagnosis")
      testGetDiagnosis
      info("Retrieving Disease")
      testGetDisease
      // testGetAllDiagnosisByCaregiver
    }
  }



}
