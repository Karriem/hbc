package crudTest

import domain.{Caregiver, Contact, Address, UnplannedVisit}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.AddressModel.AddressRepo
import repository.CaregiverModel.CaregiverRepo
import repository.ContactModel.ContactRepo
import repository.UnplannedVisitModel.UnplannedVisitRepo
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 10/7/14.
 */
class UnplannedVisitCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Unplanned Visit") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val visitRepo = TableQuery[UnplannedVisitRepo]
      val addressRepo = TableQuery[AddressRepo]
      val contactRepo = TableQuery[ContactRepo]
      val care = TableQuery[CaregiverRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        info("Creating Unplanned Visit")
        //(addressRepo.ddl).create
        //(contactRepo.ddl).create
        //(visitRepo.ddl).create

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val id = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val unplannedVisitRecord = UnplannedVisit(1L, DateTime.parse("2014-03-12").toDate, "Max", "Payne", id )
        val visitID = visitRepo.returning(visitRepo.map(_.unplannedVisitID)).insert(unplannedVisitRecord)

        val addressRecord = Address("30 Chester Road", "30 Chester Road", "7700" , None, None, None ,None , None, Some(visitID))
        val contactRecord = Contact(Some("021798000") , "0786119726", "n@gmail.com", None, None, None , None, None, Some(visitID))
        addressRepo.insert(addressRecord)
        contactRepo.insert(contactRecord)

        def Read(lastName: String, id: Long): Unit ={
          visitRepo foreach {case (u: UnplannedVisit) =>
              if(u.unplannedVisitID == id){
                assert(u.patientLastName == lastName)
              }
          }
        }

        def Update(lastName: String, id: Long): Unit ={
          visitRepo.filter(_.unplannedVisitID === id).map(_.patientLastName).update(lastName)
          Read(lastName, id)

        }

        def searchDelete(id: Long): Unit ={
          visitRepo foreach {case (u: UnplannedVisit) =>
              assertResult(false){
                visitRepo.filter(_.unplannedVisitID === id).exists.run
              }
          }

        }

        def Delete(id: Long): Unit ={
          visitRepo.filter(_.unplannedVisitID === id).delete
          searchDelete(id)
        }

        info("Reading Unplanned Visit")
        Read("Payne", visitID)
        info("Updating Unplanned Visit")
        Update("Norman", visitID)
        info("Deleting Unplanned Visit")
        Delete(visitID)

      }
    }
  }

}
