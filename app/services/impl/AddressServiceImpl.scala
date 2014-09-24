package services.impl

import domain.Address
import repository.AddressModel.AddressRepo
import repository.PatientModel.PatientRepo
import services.AddressService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/09/23.
 */
class AddressServiceImpl extends AddressService{

  val addressRepo = TableQuery[AddressRepo]
  val patientRepo = TableQuery[PatientRepo]

  override def getAddressById(id: Long) : List[AddressRepo#TableElementType] =  {

     Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


     // val patAddress = addressRepo
      val patientAddress = patientRepo.list

      val listAd = addressRepo.filter(_.caregiverId === id).list

       //val listAdress = patientAddress.filter(_.patientId == listAd.head)
      println("Address for a Person: " + listAd)

       listAd

    }
  }

  override def getAllAddresses() : List[AddressRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val addressList = addressRepo.list
      addressList
    }
  }

}
