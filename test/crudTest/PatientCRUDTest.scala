package crudTest

import domain.{Address, Adherence, Patient}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.AddressModel.AddressRepo
import repository.AdherenceModel.AdherenceRepo
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
      val adherence  = TableQuery[AdherenceRepo]
      val patAddress =TableQuery[AddressRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //if (pat.exists === false) {
         // (pat.ddl).create
        //}
        //(adherence.ddl).create

        info("Creating Patient")
        val patRecord = Patient(1, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "tonata", "nak")
        val id = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val address = Address("34 long street", "34 long street", "8000", None, None, Some(4), None, None, None)
        patAddress.insert(patAddress)

        val medication = Adherence("M144", "Apply to burnt area", id)
        adherence.insert(medication)

        def Read(name: String, id : Long) =
          pat foreach { case (patient: Patient) => {
            if (patient.patientId == id) {
              assert(patient.firstName == name)
            }
          }
          }

        adherence foreach{ case (med : Adherence) => {
            if (med.patientId == id){
              assert(med.adType == "M144")
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

          adherence.filter(_.patientId === id).delete
          pat.filter(_.patientId === id).delete
          searchDelete(id)
        }

        info("Reading Patient")
          //Read("tonata", id)

        info("Updating Patient")
          //Update("Helvi", id)

        info("Deleting Patient")
          //Delete(id)
      }
    }
  }
}
