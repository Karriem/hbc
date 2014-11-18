package crudTest

import domain.{MedicalSummary, Caregiver ,Patient}
import org.joda.time.DateTime
import repository.CaregiverModel.CaregiverRepo
import repository.MedicalSummaryModel.MedicalSummaryRepo
import repository.PatientModel.PatientRepo
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/10/08.
 */
class MedicalSummaryCRUDTest extends FeatureSpec with GivenWhenThen{
  feature("Save Measurement") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val care = TableQuery[CaregiverRepo]
      val pat = TableQuery[PatientRepo]
      val measureRepo = TableQuery[MedicalSummaryRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

       info("Creating Measurement")
       //(measureRepo.ddl).create

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val careID = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val patRecord = Patient(1, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "tonata", "nak",
                                "Tom", "0784559100" , "Christian", "English", "CPR")
        val patID = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val summaryRecord = MedicalSummary(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25,patID, careID, "Dust Allergy",
                                          "Final Diag", false, "")
        val mID = measureRepo.returning(measureRepo.map(_.medicalSummaryID)).insert(summaryRecord)

        def Read(weight: Double, id: Long) ={
          measureRepo foreach {case (m: MedicalSummary) =>
              if(m.medicalSummaryID == id){
                assert(m.weight == weight)
              }
          }
        }

        def Update(weight: Double, id: Long) ={
          measureRepo.filter(_.medicalSummaryID === id).map(_.weight).update(weight)
          Read(weight, id)

        }

        def searchDelete(id: Long) ={
          measureRepo foreach {case (m : MedicalSummary) =>
              assertResult(false){
                measureRepo.filter(_.medicalSummaryID === id).exists.run
              }
          }
        }

        def Delete(id: Long) ={
          measureRepo.filter(_.medicalSummaryID === id).delete
          searchDelete(id)
        }

        info("Reading Measurements")
        Read(65, mID)
        info("Updating Measurements")
        Update(50, mID)
        info("Deleting Measurements")
        Delete(mID)

      }
    }
  }

}
