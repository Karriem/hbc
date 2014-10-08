package services

import domain.{Contact, Address, Caregiver, UnplannedVisit}
import org.joda.time.DateTime
import repository.CaregiverModel.CaregiverRepo
import repository.UnplannedVisitModel.UnplannedVisitRepo
import org.scalatest.{GivenWhenThen, FeatureSpec}
import services.impl.UnplannedVisitServiceImpl
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/10/08.
 */
class UnplannedVisitServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Unplanned Visit service") {
    info("I want to carry out specific unplanned visit services")

    scenario("Creating object instances") {
      Given("Specific entity information")

      val visitRepo = TableQuery[UnplannedVisitRepo]
      val care = TableQuery[CaregiverRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val id = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val unplannedVisitRecord = UnplannedVisit(1L, DateTime.parse("2014-03-12").toDate, "Max", "Payne", 1L )

        val addressRecord = Address("30 Chester Road", "30 Chester Road", "7700" , None, None, None ,None , None, None)
        val contactRecord = Contact(Some("021798000") , "0786119726", "n@gmail.com", None, None, None , None, None, None)

        val visitService: UnplannedVisitService = new UnplannedVisitServiceImpl()

        def testCreateUnplannedVisit(): Unit ={
          visitService.createUnplannedVisit(unplannedVisitRecord, id, addressRecord , contactRecord)
          visitRepo foreach {case (v: UnplannedVisit) =>
              if(v.caregiverID == id){
                assert(v.patientName == "Max")
              }
          }
        }

        def testListAllUnplannedVisits(): Unit ={
          val allUnplannedVisits = visitService.listAllUnplannedVisits()
          allUnplannedVisits foreach {case (u: UnplannedVisit) =>
              if(u.caregiverID == id){
                assert(u.patientName == "Max")
              }
          }
        }

        info("Testing Creating unplanned visits")
        testCreateUnplannedVisit()
        info("Testing List all Unplanned visits")
        testListAllUnplannedVisits()

      }
    }
  }

}
