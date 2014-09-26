package services

import domain.Contact
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait ContactService {
  def getContact(id : Long): Contact //List[ContactPersonRepo#TableElementType]
  def getAllContacts() : List[ContactRepo#TableElementType]
}
