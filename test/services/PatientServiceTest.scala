package services

import domain.Patient
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import services.impl.PatientServiceImpl

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

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
      val careplanRepo = TableQuery[CarePlanRepo]

      val patientservice: PatientService = new PatientServiceImpl
      val patient = Patient(4, DateTime.parse("2014-10-03").toDate, DateTime.parse("2014-10-03").toDate, "Buhle", "Ntshewula")

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def createPatient: Unit = {
          val patient = Patient(4, DateTime.parse("2014-10-03").toDate, DateTime.parse("2014-10-03").toDate, "Buhle", "Ntshewula")

          val value = patientservice.addPatient(patient)
          println("Patient" + value)
          assert(patientRepo.list.filter(_.patientId == value).head.firstName == "Buhle")

        }

        def getDiagnosis {

          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            val value = patientservice.getDiagnosis(44)
            assert(diagnosisRepo.list.filter(_.dailyReportId == value.dailyReportId).head.diagnosisType == "Flu")

          }

        }

        def getCarePlan {

          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            val value = patientservice.displayCarePlan(0)
            assert(value.description == "TB Treatment")
          }
        }

          info("Testing create patient")
           createPatient
          info("Testing get diagnosis")
           getDiagnosis
          info("Testing get careplan")
           getCarePlan
        }
      }
    }

  }
