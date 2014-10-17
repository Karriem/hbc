package services

import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AdherenceModel.AdherenceRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import services.impl.PatientServiceImpl

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by phakama on 2014/09/24.
 */
class PatientServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Implementing Patient Service") {
    info("Using Patient Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val patientRepo = TableQuery[PatientRepo]
      val diagnosisRepo = TableQuery[DiagnosisRepo]
      val adherenceRepo = TableQuery[AdherenceRepo]

      val patientservice: PatientService = new PatientServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def testGetDiagnosis {

          val value = patientservice.getDiagnosis(5)
          assert(diagnosisRepo.list.filter(_.dailyReportId == value.dailyReportId).head.diagnosisType == "Burn wounds")
        }

        def testGetAdherence: Unit = {

          val value = patientservice.getAdherence(404)
          println("Adherence: " + value)
          assert(adherenceRepo.list.filter(_.patientId == value.patientId).head.adType == "M144")
        }

        info("Testing get diagnosis")
        testGetDiagnosis

        info("Testing get adherence")
        testGetAdherence
      }
      }
    }
}



