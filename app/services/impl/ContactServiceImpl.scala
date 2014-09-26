package services.impl

import domain.Contact
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import services.ContactService
import scala.collection.mutable.ListBuffer
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by phakama on 2014/09/23.
 */
class ContactServiceImpl extends ContactService{

  val contactRepo = TableQuery[ContactRepo]

  override def getContact(id: Long): Contact = {//List[ContactPersonRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contact = contactRepo.filter(_.personId === id).list

      println("Getting a contact person " + contact.head)
      contact.head
    }
    }

  override def getAllContacts(): List[ContactRepo#TableElementType] ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contacts = contactRepo.list
      contacts
    }
    }
}
