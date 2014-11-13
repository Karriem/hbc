package crudTest

import domain._
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.AddressModel.AddressRepo
import repository.AdherenceModel.AdherenceRepo
import repository.ContactModel.ContactRepo
import repository.DemographicModel.DemographicRepo
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
      val contact = TableQuery[ContactRepo]
      val demoRepo = TableQuery[DemographicRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        (pat.ddl).create
        (adherence.ddl).create
        (demoRepo.ddl).create

        info("Creating Patient")
        val patRecord = Patient(7, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "Chris", "Johnson" ,
                                "Tom", "0784559100" , "Christian", "English", "CPR")
        val id = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val medication = Adherence("M144", "Apply to burnt area", id, 1L)
        adherence.insert(medication)

        val address = Address("34 long street", "34 long street", "8000", None, None, Some(id), None, None, None)
        patAddress.insert(address)

        val demo = Demographic(32, "male", DateTime.parse("1979-04-25").toDate, None, None, Some(id), None)
        demoRepo.insert(demo)

        val contactRecord = Contact(None, "07983464", "mk@yahoo", None, None, None, Some(id), None, Some(2))
        contact.insert(contactRecord)

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
          Read("Chris", id)

        info("Updating Patient")
          Update("Helvi", id)

        info("Deleting Patient")
          Delete(id)
      }
    }
  }
}
