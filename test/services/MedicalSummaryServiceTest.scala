package services

import domain.{MedicalSummary, Caregiver, Patient}
import org.joda.time.DateTime
import repository.CaregiverModel.CaregiverRepo
import repository.MedicalSummaryModel.MedicalSummaryRepo
import repository.PatientModel.PatientRepo
import org.scalatest.{GivenWhenThen, FeatureSpec}
import services.impl.MedicalSummaryServiceImpl
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/10/08.
 */
class MedicalSummaryServiceTest extends FeatureSpec with GivenWhenThen{
  feature("Measurement service") {
    info("I want to carry out specific measurement services")

    scenario("Creating object instances") {
      Given("Specific entity information")

      val care = TableQuery[CaregiverRepo]
      val pat = TableQuery[PatientRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val careID = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val patRecord = Patient(1, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "tonata", "nak", "Stuff", "24548844", "Stuff", "Japanese", "Things")
        val patID = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val measurementRecord = MedicalSummary(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25, 1L, 1L, "Things", "Stuff", true, "Stuff")

        val mService: MedicalSummaryService = new MedicalSummaryServiceImpl()
        val measureRepo = TableQuery[MedicalSummaryRepo]
        var id: Long = 0L

        def testCreateMeasurement(): Unit ={
           id = mService.createMeasurement(measurementRecord/*, patID, careID*/)
           measureRepo foreach {case (m: MedicalSummary) =>
               if(m.medicalSummaryID == id){
                 assert(m.weight == 65)
               }
           }
        }

        def testGetMeasurements(): Unit ={
          val measurementsList = mService.getMeasurements(patID)
          measurementsList foreach {case (m: MedicalSummary) =>
             if(m.medicalSummaryID == id){
               assert(m.weight == 65)
             }
          }
        }

        info("Test Creating Measurements")
        testCreateMeasurement()
        info("Test Get Measurements")
        testGetMeasurements()

      }
    }
  }

}
