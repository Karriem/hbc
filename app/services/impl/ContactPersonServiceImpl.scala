package services.impl

import domain.{Institution, ContactPerson, Contact}
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import services.ContactPersonService
import scala.collection.mutable.ListBuffer
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

      val value = contactRepo.filter(_.instituteId === id).list
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
