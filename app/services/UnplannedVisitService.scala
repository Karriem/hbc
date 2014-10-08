package services

import domain.{Contact, Address, UnplannedVisit}
import repository.UnplannedVisitModel.UnplannedVisitRepo

/**
 * Created by tonata on 2014/10/08.
 */
trait UnplannedVisitService {

  def createUnplannedVisit(visit: UnplannedVisit,
                            caregiverID: Long,
                            address: Address,
                            contact: Contact)

  def listAllUnplannedVisits(): List[UnplannedVisitRepo#TableElementType]

}
