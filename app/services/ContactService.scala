package services

import repository.ContactPersonModel.ContactPersonRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait ContactService {
  def getContact(id : Long): List[ContactPersonRepo#TableElementType]
  def getAllContacts(id : Long) : List[ContactPersonRepo#TableElementType]
}
