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

        val patRecord = Patient(1, "20/05/2014", "2/08/2014", "tonata", "nak")

        //val id = pat.returning(pat.map(_.patientId)).insert(patRecord)

        def Read =
          pat foreach { case (patient : Patient) =>
          //println( "Name: " + patient.firstName)
          if (patient.patientId == 17){
            assert(patient.firstName == "fght")
            println(patient.firstName)
            println(patient.patientId)
          }
        }


        info("Reading Patient")
        Read


        //info("Update Patient")

        //info("Delete Patient")
        //println(value)
        //assert(pat.where(_.patientId == value).map(_.firstName == "Karriem"))

        //for {
         // p <- pat
          //p <- pat.filter(_.patientId === value)
          //assertResult((p.lastName === "Kurosaki"), true)
          //assert(p.lastName == "Kurosaki")
        //} yield p
        //val addresses: Seq[Address] = people.map(_.address)
      }
    }
  }
}
