package crudTest


import domain.{Address, Coordinator, Demographic}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AddressModel.AddressRepo
import repository.ContactModel.ContactRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.DemographicModel.DemographicRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/10/14.
 */
class CoordinatorCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Caregiver") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val coordinatorRepo= TableQuery[CoordinatorRepo]
      val addressRepo = TableQuery[AddressRepo]
      val contactRepo = TableQuery[ContactRepo]
      val demoRepo = TableQuery[DemographicRepo]
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        // (addressRepo.ddl).create
        // (coordinatorRepo.ddl).create
        //(contactRepo.ddl).create
        //(demoRepo.ddl).create

        val coordinatorRecord = Coordinator(1,  "Nikki", "Shiyagaya")

        val id = coordinatorRepo.returning(coordinatorRepo.map(_.coId)).insert(coordinatorRecord)

        val addressRecord = Address("30 Chester Road", "30 Chester Road" , "7700" , Some(0), Some(0), Some(0) , Some(0) , Some(id), None)
        val demoRecord = Demographic(23 ,"Female", DateTime.parse("1976-03-16").toDate , Some(id), Some(0), None , None )

        addressRepo.insert(addressRecord)
        demoRepo.insert(demoRecord)

        def Read(age: Int, id : Long , cellNumber: String, address: String) = {
          demoRepo foreach { case (demographic: Demographic) =>
            if (demographic.coordinatorId == Option(id)) {
              assert(demographic.age == age)
            }
          }

          addressRepo foreach { case (add: Address) =>
            if (add.coordinatorId == Option(id)) {
              assert(add.streetAddress == address)
            }

          }

        }

        def Update(name:String, id:Long, age: Int, cellNumber: String, address: String) = {
          coordinatorRepo.filter(_.coId === id).map(_.firstName).update(name)
          demoRepo.filter(_.coordinatorId === id).map(_.age).update(age)
          addressRepo.filter(_.coordinatorId === id).map(_.streetAddress).update(address)
          Read(age, id, cellNumber, address)
        }

        def searchDelete(id: Long) : Int = {
          coordinatorRepo foreach { case (c: Coordinator) =>
            assertResult(false) {
              coordinatorRepo.filter(_.coId === id).exists.run
            }
          }
          return 0;
        }

        def Delete(id:Long) = {
          demoRepo.filter(_.coordinatorId === id).delete
          addressRepo.filter(_.coordinatorId === id).delete
          coordinatorRepo .filter(_.coId === id).delete
          searchDelete(id)
        }

        info("Reading Coordinator Details ")
        Read(23, id, "0786119726", "30 Chester Road")

        info("Updating Coordinator Details")
        Update("Maxine" , id, 27 , "0823349090", "402 Apple Street")

        info("Deleting Coordinator")
        Delete(id)
      }
    }
  }
}



