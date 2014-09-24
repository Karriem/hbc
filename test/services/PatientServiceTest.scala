package services

import domain.{Diagnosis, Patient}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CarePlanModel.CarePlanRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import services.impl.PatientServiceImpl

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/09/24.
 */
class PatientServiceTest extends FeatureSpec with GivenWhenThen{

  feature("Implementing Patient Service") {
    info("Using Patient Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val patientRepo = TableQuery[PatientRepo]
      val diagnosisRepo = TableQuery[DiagnosisRepo]
      val careplan = TableQuery[CarePlanRepo]

      val obj : PatientService = new PatientServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def createPatient: Unit = {
          val patient = Patient(2,"03/10/2014", "03/10/2014", "Phakama", "Ntshewula")

          val value = obj.addPatient(patient)
          println("Patient" + value)
          assert(patientRepo.list.filter(_.patientId == value).head.firstName == "Phakama")

        }
        def updatePatient: Unit ={
          val patientList = patientRepo.list
          val pat = patientList.filter(_.patientId == 2)

          val newPatient = Patient(17, pat.head.dateOfContact, pat.head.dateOfEvaluation, "Tonata", "Nakashololo")

        }

        def testCeatePatient ={
          val value = obj.getDiagnosis(2)
          //aseert(value.)
        }
        info("getting diagnosis")
        createPatient
      }
    }
  }

}
