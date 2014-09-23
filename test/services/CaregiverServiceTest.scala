package services

import domain.Patient
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.PatientModel.PatientRepo
import services.impl.CaregiverServiceImpl

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by karriem on 9/23/14.
 */
class CaregiverServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Implementing Caregiver Service") {
    info("Using Caregiver Service")
    info("Tesing if service works")

    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val careObj : CaregiverService = new CaregiverServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getCarePlanTest ={

          val obj = careObj.getCareplan(5)
          assert(obj.description == "Caring for elder")
        }

        def getPatientDetailsTest = {

          val obj = careObj.getPatientDetails(5)
          assert(obj.firstName == "Phakama")
        }

        def getUserDetailsTest ={

          val obj = careObj.getUserDetails(5)
          assert(obj.username == "Sasuke")
        }

        def addPatientTest = {
          val patRecord = Patient(1, "20/05/2014", "2/08/2014", "MIMI", "No")
          val value = careObj.addPatient(patRecord)
          val repo = TableQuery[PatientRepo]
          assert(repo.list.filter(_.patientId == value).head.firstName == "MIMI")
        }

        info("GetCarePlanTest")
        getCarePlanTest
        info("GetPatientDetailsTest")
        getPatientDetailsTest
        info("GetUserDetailsTest")
        getUserDetailsTest
        info("AddPatientTest")
        addPatientTest
      }
    }
  }
}
