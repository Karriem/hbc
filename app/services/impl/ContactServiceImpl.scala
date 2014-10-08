package services.impl

import domain._
import repository.ContactModel.ContactRepo
import services.ContactService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/10/08.
 */
class ContactServiceImpl  extends ContactService{

  val contactRepo = TableQuery[ContactRepo]

  override def getPersonContact(id: Long): Contact = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val person = contactRepo.filter(_.personId === id).list
      person.head
    }
  }

  override def getInstituteContact(id: Long): Contact = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val institute = contactRepo.filter(_.instituteId === id).list
      institute.head
    }
  }

  override def getAllContacts(): List[ContactRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contacts = contactRepo.list
      contacts
    }
  }

  override def getCaregiverContact(id: Long): Contact = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val caregiver = contactRepo.filter(_.caregiverId === id).list
      caregiver.head
    }
  }

  override def getCoordinatorContact(id: Long): Contact = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coordinator = contactRepo.filter(_.coordinatorId === id).list
      coordinator.head
    }

  }

  override def getPatientContact(id: Long): Contact = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patient = contactRepo.filter(_.patientId === id).list
      patient.head
    }
  }
}
