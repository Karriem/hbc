package crudTest

import domain.{Caregiver, Measurement, Patient}
import org.joda.time.DateTime
import repository.CaregiverModel.CaregiverRepo
import repository.MeasurementModel.MeasurementRepo
import repository.PatientModel.PatientRepo
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/10/08.
 */
class MeasurementCRUDTest extends FeatureSpec with GivenWhenThen{
  feature("Save Measurement") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val care = TableQuery[CaregiverRepo]
      val pat = TableQuery[PatientRepo]
      val measureRepo = TableQuery[MeasurementRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        info("Creating Measurement")
       // (measureRepo.ddl).create

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val careID = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val patRecord = Patient(1, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "tonata", "nak")
        val patID = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val measurementRecord = Measurement(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25,patID, careID )
        val mID = measureRepo.returning(measureRepo.map(_.measurementID)).insert(measurementRecord)

        def Read(weight: Double, id: Long) ={
          measureRepo foreach {case (m: Measurement) =>
              if(m.measurementID == id){
                assert(m.weight == weight)
              }
          }
        }

        def Update(weight: Double, id: Long) ={
          measureRepo.filter(_.measurementID === id).map(_.weight).update(weight)
          Read(weight, id)

        }

        def searchDelete(id: Long) ={
          measureRepo foreach {case (m : Measurement) =>
              assertResult(false){
                measureRepo.filter(_.measurementID === id).exists.run
              }
          }
        }

        def Delete(id: Long) ={
          measureRepo.filter(_.measurementID === id).delete
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
