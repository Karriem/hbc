package crudTest

import domain.{Coordinator, Patient, CarePlan}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class CarePlanCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Care Plan") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val care = TableQuery[CarePlanRepo]
      val pat = TableQuery[PatientRepo]
      val co = TableQuery[CoordinatorRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(care.ddl).create
        //(co.ddl).create
        //(pat.ddl).create

        info("Creating a Care Plan")
        val patient = Patient(1, "8/02/2012", "10/02/2012", "Bo", "Micheals")
        val coor = Coordinator(1, "Lou", "Smith")

        val idp = pat.returning (pat.map (_.patientId))insert(patient)
        val idc = co.returning (co.map (_.coId) )insert(coor)

        val careplan = CarePlan(1, "Caring for elder", "5/05/2014", "5/05/2014", idp, idc)
        val id = care.returning (care.map (_.planId)).insert(careplan)


        def Read(name:String, id:Long) = {
          care foreach { case (careplan: CarePlan) =>
            if (careplan.patientId == id) {
              pat foreach { case (patient: Patient) =>
                if (patient.patientId == careplan.patientId) {
                  assert(patient.firstName == name)
                }
              }
            }
          }
        }

        def Update(desc:String, id:Long) = {
          care.filter(_.planId === id).map(_.description).update(desc)
          care foreach { case (careplan: CarePlan) =>
            if (careplan.planId == id) {
                  assert(careplan.description == desc)
            }
          }
        }

        def searchDelete(id: Long) : Int = {
          care foreach { case (cr: CarePlan) =>
            assertResult(false) {
              care.filter(_.planId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          care.filter(_.planId === id).delete
          searchDelete(id)
        }

        info("Reading Care Plan")
        Read("Bo", idp)
        info("Updating Care Plan")
        Update("Cleaning Patient", id)
        info("Deleting Care Plan")
        Delete(id)
      }
    }
  }
}