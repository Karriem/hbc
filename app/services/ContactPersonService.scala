package services

import domain.{ContactPerson, Contact}
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait ContactPersonService {
  def getContact(id : Long): ContactPerson //List[ContactPersonRepo#TableElementType]
  def getAllContacts() : List[ContactPersonRepo#TableElementType]
}
