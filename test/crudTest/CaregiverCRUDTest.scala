package crudTest

import domain._
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AddressModel.AddressRepo
import repository.CaregiverModel.CaregiverRepo
import repository.ContactModel.ContactRepo
import repository.DemographicModel.DemographicRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/10/14.
 */
class CaregiverCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Caregiver") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val care = TableQuery[CaregiverRepo]
      val addressRepo = TableQuery[AddressRepo]
      val contactRepo = TableQuery[ContactRepo]
      val demoRepo = TableQuery[DemographicRepo]
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        (demoRepo.ddl).create
        (contactRepo.ddl).create
        (addressRepo.ddl).create
        (adRepo.ddl).create

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")

        val id = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val addressRecord = Address("30 Chester Road", "30 Chester Road", "7700" , Some(0), Some(0), Some(0) , Some(id) , Some(0), None)
        val contactRecord = Contact(Some("021798000") , "0786119726", "n@gmail.com", Some(0), Some(0), Some(0) , Some(0), Some(id), None)
        val demoRecord = Demographic(23 ,"Female", DateTime.parse("1976-03-16").toDate , Some(0), Some(0), Some(0) , Some(id) )

        addressRepo.insert(addressRecord)
        contactRepo.insert(contactRecord)
        demoRepo.insert(demoRecord)

        def Read(age: Int, id : Long , cellNumber: String, address: String) = {
          demoRepo foreach { case (demographic: Demographic) =>
            if (demographic.caregiverId == Option(id)) {
              assert(demographic.age == age)
            }
          }
          contactRepo foreach { case (con: Contact) =>
              if (con.caregiverId == Option(id)) {
                assert(con.cellNumber == cellNumber)
              }

            }
          addressRepo foreach { case (add: Address) =>
            if (add.caregiverId == Option(id)) {
              assert(add.streetAddress == address)
            }

          }

        }
        def Update(name:String, id:Long, age: Int, cellNumber: String, address: String) = {
          care.filter(_.caregiverId === id).map(_.firstName).update(name)
          demoRepo.filter(_.caregiverId === id).map(_.age).update(age)
          contactRepo.filter(_.caregiverId === id).map(_.cellNumber).update(cellNumber)
          addressRepo.filter(_.caregiverId === id).map(_.streetAddress).update(address)
          Read(age, id, cellNumber, address)
        }

        def searchDelete(id: Long) : Int = {
          care foreach { case (cr: Caregiver) =>
            //println("/////" + id)
            assertResult(false) {
              care.filter(_.caregiverId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          demoRepo.filter(_.caregiverId === id).delete
          contactRepo.filter(_.caregiverId === id).delete
          addressRepo.filter(_.caregiverId === id).delete
          care.filter(_.caregiverId === id).delete
          searchDelete(id)
        }

        info("Reading Caregiver Details ")
        Read(23, id, "0786119726", "30 Chester Road")

        info("Updating Caregiver Details")
        Update("Maxine" , id, 27 , "0823349090", "402 Apple Street")

        info("Deleting Caregiver")
         Delete(id)
      }
    }
  }
}
