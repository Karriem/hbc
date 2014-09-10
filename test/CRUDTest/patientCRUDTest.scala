package CRUDTest

import domain.Patient
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.PatientModel.PatientRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/9/14.
 */
class patientCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Patient") {
    info("As a Caregiver")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val pat = TableQuery[PatientRepo]

      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //if (pat.exists === false) {
         // (pat.ddl).create
        //}
        info("Creating Patient")
        val patRecord = Patient (1, "20/05/2014", "2/08/2014", "tonata", "nak")

        val id = pat.returning (pat.map (_.patientId) ).insert (patRecord)


        def Read(name: String, id : Long) =
          pat foreach { case (patient : Patient) =>
          if (patient.patientId == id){
            assert(patient.firstName == name)
          }
        }

        def Update(name:String, id:Long) = {
          pat.filter(_.patientId === id).map(_.firstName).update(name)
          Read(name, id)
        }

        def searchDelete(id:Long) = {
          pat foreach { case (patient: Patient) =>
            println("/////" + id)
            assertResult(true) {

              if (patient.patientId == id) {
                true
              }
              else
                false
            }
          }
        }

        def Delete(id:Long) = {
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
