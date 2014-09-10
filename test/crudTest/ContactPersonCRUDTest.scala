package crudTest

import domain.ContactPerson
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.ContactPersonModel.ContactPersonRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class ContactPersonCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Patient") {
    info("As a Caregiver")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val con = TableQuery[ContactPersonRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        (con.ddl).create

        val contact = ContactPerson(1, "Lola", "Fords")

        val id = con.returning (con.map (_.personId) ).insert(contact)

        def Read(name: String, id : Long) = {
          con foreach { case (contact: ContactPerson) =>
            if (contact.personId == id) {
              assert(contact.LastName == name)
            }
          }
        }

        def Update(name:String, id:Long) = {
          con.filter(_.personId === id).map(_.lastName).update(name)
          Read(name, id)
        }

        def Delete(id:Long) = {
          con.filter(_.personId === id).delete
        }

        info("Reading Contact Person")
        Read("Fords", id)
        info("Updating Contact Person")
        Update("Karen", id)
        info("Deleting Contact Person")
        Delete(id)
      }
    }
  }
}
