package services

import domain._
import repository.ContactModel.ContactRepo

/**
 * Created by phakama on 2014/10/08.
 */
trait ContactService {
  def getPersonContact(id: Long): Contact
  def getInstituteContact(id: Long): Contact
  def getCoordinatorContact(id: Long): Contact
  def getCaregiverContact(id: Long): Contact
  def getPatientContact(id: Long): Contact
  def getAllContacts() : List[ContactRepo#TableElementType]

}
