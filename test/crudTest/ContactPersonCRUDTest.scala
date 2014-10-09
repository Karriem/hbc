package crudTest

import java.util.Date

import domain.{Demographic, Contact, Address, ContactPerson}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AddressModel.AddressRepo
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.DemographicModel.DemographicRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class ContactPersonCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Contact Person") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario("Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val con = TableQuery[ContactPersonRepo]
      val addressRepo = TableQuery[AddressRepo]
      val contactRepo = TableQuery[ContactRepo]
      val demoRepo = TableQuery[DemographicRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(con.ddl).create
        val contactAdd = Address("45 Samora", "45 Samora", "7785", Some(25), None, None, None, None, None )
        addressRepo.insert(contactAdd)

        val contactPerson = ContactPerson(1, "Lola", "Fords")
        val id = con.returning (con.map (_.personId) ).insert(contactPerson)

        val contact = Contact(Some("0213433"), "08324545", "k@gbhosp.com", Some(id), None, None, None, None, None)
        contactRepo.insert(contact)

        val demo = Demographic(31, "male", DateTime.parse("1979-02-14").toDate, None, Some(id), None, None)
        demoRepo.insert(demo)

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

        def searchDelete(id: Long) : Int = {
          con foreach { case (cp: ContactPerson) =>
            assertResult(false) {
              con.filter(_.personId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          con.filter(_.personId === id).delete
          searchDelete(id)
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
