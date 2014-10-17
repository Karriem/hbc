package services.impl

import domain.ContactPerson
import repository.ContactPersonModel.ContactPersonRepo
import services.ContactPersonService

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by phakama on 2014/09/23.
 */
class ContactPersonServiceImpl extends ContactPersonService{

//  val instituteRepo = TableQuery[Institution]
  val contactRepo = TableQuery[ContactPersonRepo]

  override def getContact(id: Long): ContactPerson = {//List[ContactPersonRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //val person = contactRepo.list
      //val institution = instituteRepo.list
      val list = contactRepo.list
      val value = list.filter(_.instituteId == id)
      value.head

    }
    }

  override def getAllContacts(): List[ContactPersonRepo#TableElementType] ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contacts = contactRepo.list
      contacts
    }
    }
}
