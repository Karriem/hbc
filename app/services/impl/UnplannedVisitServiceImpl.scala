package services.impl

import domain.{Contact, Address, UnplannedVisit}
import repository.AddressModel.AddressRepo
import repository.CaregiverModel.CaregiverRepo
import repository.ContactModel.ContactRepo
import repository.UnplannedVisitModel.UnplannedVisitRepo
import services.UnplannedVisitService

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 2014/10/08.
 */
class UnplannedVisitServiceImpl extends UnplannedVisitService {

  val visitRepo = TableQuery[UnplannedVisitRepo]
  val addressRepo = TableQuery[AddressRepo]
  val contactRepo = TableQuery[ContactRepo]
  val care = TableQuery[CaregiverRepo]

  override def createUnplannedVisit(visit: UnplannedVisit,
                                     caregiverID: Long,
                                     address: Address,
                                     contact: Contact): Unit = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val unplannedVisitRecord = UnplannedVisit(visit.unplannedVisitID, visit.visitDate, visit.patientName,
      visit.patientLastName, caregiverID)
      val visitID = visitRepo.returning(visitRepo.map(_.unplannedVisitID)).insert(unplannedVisitRecord)

      val addressRecord = Address("30 Chester Road", "30 Chester Road", "7700" , None, None, None ,None , None, Some(visitID))
      addressRepo.insert(addressRecord)

      val contactRecord = Contact(Some("021798000") , "0786119726", "n@gmail.com", None, None, None , None, None, Some(visitID))
      contactRepo.insert(contactRecord)
    }
  }

  override def listAllUnplannedVisits(): List[UnplannedVisitRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return visitRepo.list
    }
  }

}
