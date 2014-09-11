package crudTest

import domain.{Medication, Patient}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.MedicationModel.MedicationRepo
import repository.PatientModel.PatientRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/9/14.
 */
class PatientCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Patient") {
    info("As a Caregiver")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val pat = TableQuery[PatientRepo]
      val med  = TableQuery[MedicationRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //if (pat.exists === false) {
         // (pat.ddl).create
        //}
        //(med.ddl).create

        info("Creating Patient")
        val patRecord = Patient(1, "20/05/2014", "2/08/2014", "tonata", "nak")
        val id = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val medication = Medication("M144", "Apply to burnt area", id)
        med.insert(medication)

        def Read(name: String, id : Long) =
          pat foreach { case (patient: Patient) => {
            if (patient.patientId == id) {
              assert(patient.firstName == name)
            }
          }
          }

          med foreach{ case (med : Medication) => {
            if (med.patientId == id){
              assert(med.mType == "M144")
            }
          }
        }

        def Update(name:String, id:Long) = {
          pat.filter(_.patientId === id).map(_.firstName).update(name)
          Read(name, id)
        }

        def searchDelete(id: Long) : Int = {
          pat foreach { case (cr: Patient) =>
            assertResult(false) {
              pat.filter(_.patientId === id).exists.run
            }
          }

          return 0;
        }

        def Delete(id:Long) = {

          med.filter(_.patientId === id).delete
          pat.filter(_.patientId === id).delete
          searchDelete(id)
        }

        info("Reading Patient")
        Read("tonata", id)
        info("Updating Patient")
        Update("Helvi", id)
        info("Deleting Patient")
        Delete(id)
      }
    }
  }
}
