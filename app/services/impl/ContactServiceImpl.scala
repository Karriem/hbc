package services.impl

import repository.ContactPersonModel.ContactPersonRepo
import services.ContactService
import scala.collection.mutable.ListBuffer
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by phakama on 2014/09/23.
 */
class ContactServiceImpl extends ContactService{

  val contactRepo = TableQuery[ContactPersonRepo]

  override def getContact(id: Long): List[ContactPersonRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contactPerson = contactRepo.filter(_.personId === id).list

      println("Getting a contact person " + contactPerson)
      contactPerson
    }
    }

  override def getAllContacts(id : Long): List[ContactPersonRepo#TableElementType] ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contacts = contactRepo.filter(_.personId === id ).map(_.personId)
      var con = new ListBuffer[ContactPersonRepo#TableElementType]()

      contacts foreach{case (id: Long) =>
        val obj = contactRepo.filter(_.personId === id).list.head
        con += obj
      }
      val contactList = con.toList
      return contactList
    }
    }
}
