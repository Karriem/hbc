package services

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import services.impl.ContactPersonServiceImpl
import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by phakama on 2014/09/26.
 */
class ContactPersonServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Implementing Contact person Service") {
    info("Using ContactPerson Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val contactservice : ContactPersonService = new ContactPersonServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getContact: Unit = {
          val value = contactservice.getContact(5)
          assert(value.firstName == "Lola")
        }

        def getAllContacts: Unit ={
          var contacts : List[ContactPersonRepo#TableElementType] = List()
          contacts = contactservice.getAllContacts()
          assert(contacts.size == 1)
        }

        info("Contact: ")
        getContact

        info("All contacts")
        getAllContacts
      }
      }
  }
}
