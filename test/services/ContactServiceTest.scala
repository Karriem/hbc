package services

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.ContactModel.ContactRepo
import services.impl.ContactServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/10/08.
 */
class ContactServiceTest extends FeatureSpec with GivenWhenThen{
  feature("Implementing Contact person Service") {
    info("Using ContactPerson Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val contactservice: ContactService = new ContactServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getCaregiverContact: Unit ={

          val value = contactservice.getCaregiverContact(141)
          assert(value.email == "dfgwergh")
        }

        def getCoordinatorContact: Unit ={

          val value = contactservice.getCoordinatorContact(269)
          assert(value.cellNumber == "0798734654")
        }

        def getContactPerson: Unit ={

          val value = contactservice.getPersonContact(30)
          assert(value.email == "k@gbhosp.com")
        }

        def getPatientDetails: Unit ={

          val value = contactservice.getPatientContact(437)
          assert(value.cellNumber == "07983464")
        }

        def getInstituteDetails: Unit ={

          val value = contactservice.getInstituteContact(91)
          assert(value.cellNumber == "08245789")
        }

        def getAllContacts: Unit ={

          var contactList: List[ContactRepo#TableElementType] = List()
          contactList = contactservice.getAllContacts()
          //println("Contact list: " + contactList)
          assert(contactList.size == 6)
        }

        info("Testing caregiver contact details")
        getCaregiverContact

        info("Testing coordinator contact details")
        getCoordinatorContact

        info("Testing contact person contact details")
        getContactPerson

        info("Testing patient contact details")
        getPatientDetails

        info("Testing institution contact details")
        getInstituteDetails

        info("Testing all contact details")
        //getAllContacts
      }

    }
  }

}
