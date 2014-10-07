package services

import domain.{Adherence, Patient}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AdherenceModel.AdherenceRepo
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
      val adherenceRepo = TableQuery[AdherenceRepo]

      val patientservice: PatientService = new PatientServiceImpl
      val patient = Patient(4, DateTime.parse("2014-10-03").toDate, DateTime.parse("2014-10-03").toDate, "Buhle", "Ntshewula")
      val adherence = Adherence("Laxatives", "take 1, 3 times a day", 110)

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def testCreatePatient: Unit = {
          val patient = Patient(4, DateTime.parse("2014-10-03").toDate, DateTime.parse("2014-10-03").toDate, "Buhle", "Ntshewula")

          val value = patientservice.addPatient(patient)
          println("Patient" + value)
          assert(patientRepo.list.filter(_.patientId == value).head.firstName == "Buhle")

        }

        def testGetDiagnosis {

          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            val value = patientservice.getDiagnosis(31)
            assert(diagnosisRepo.list.filter(_.dailyReportId == value.dailyReportId).head.diagnosisType == "Flu")
          }
        }

        def testCreateAdherence {
          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            val value = patientservice.createAdherence(adherence)
            println("Adherence" + value)
            assert(adherenceRepo.list.filter(_.patientId == value).head.adType == "Laxatives")
           // assert(value.)
          }

          }

        def testGetAdherence: Unit ={
          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            val value = patientservice.getAdherence(110)
            println("Adherence: " + value)
            assert(adherenceRepo.list.filter(_.patientId == value.patientId).head.adType == "M144")
          }

          }

          info("Testing create patient")
            testCreatePatient
          info("Testing get diagnosis")
            testGetDiagnosis
          info("Testing create adherence")
           //testCreateAdherence
          info("Testing get adherence")
            testGetAdherence
        }
      }
    }

  }
