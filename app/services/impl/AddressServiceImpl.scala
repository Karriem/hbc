package services.impl

import domain.{Institution, Address}
import repository.AddressModel.AddressRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import services.AddressService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/09/23.
 */
class AddressServiceImpl extends AddressService{

  val addressRepo = TableQuery[AddressRepo]
  val patientRepo = TableQuery[PatientRepo]
  val coordinatorRepo = TableQuery[CoordinatorRepo]
  val contactRepo = TableQuery[ContactPersonRepo]
  val instituteRepo = TableQuery[InstitutionRepo]

  override def getCaregiverAddress(id: Long) : Address={
     Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val listAd = addressRepo.filter(_.caregiverId === id).list

       listAd.head

    }
  }

  override def getAllAddresses() : List[AddressRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val addressList = addressRepo.list
      addressList
    }
  }

  override def getCoordinatorAddress(id: Long): Address = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coord = addressRepo.filter(_.coordinatorId === id).list

      coord.head

    }
    }

  override def getContactPersonAddress(id: Long): Address = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val contact = addressRepo.filter(_.personId === id).list

      contact.head
    }
    }

  override def getPatientAddress(id: Long): Address = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patient = addressRepo.filter(_.patientId === id).list

      patient.head

    }
    }

  override def getInstituteAddress(id: Long): Address = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val institute = addressRepo.filter(_.instituteId === id).list

      println("Institution address" + institute)
      institute.head
    }
  }
}
