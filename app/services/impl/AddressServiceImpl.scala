package services.impl

import domain.Address
//import org.h2.engine.Database
import scala.slick.driver.MySQLDriver.simple._
import repository.AddressModel.AddressRepo
import repository.PatientModel.PatientRepo
import services.AddressService

import scala.slick.lifted.TableQuery

/**
 * Created by phakama on 2014/09/23.
 */
class AddressImpl extends AddressService {
  val db = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin")//.withSession { implicit session =>
  val addressRepo = TableQuery[AddressRepo]
  val patRepo = TableQuery[PatientRepo]

  override def getAddressById(id: Long)
  {
    val db = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      /* // db.withSession {implicit session =>
      addressRepo.firstOption
      addressRepo.filter(_.postalAddress === "30 Chester Road")
      addressRepo.length.run
      println("Adress: "+ addressRepo)*/

      val patAddress = addressRepo.list
      val patientAddress = patRepo.list

      val listAd = patAddress.filter(_.postalAddress == id).map(_.patientId)

      val listAdress = patientAddress.filter(_.patientId == listAd.head)
      println("Address: " + listAdress)
    }


  }

  override def getAllAddresses(addresses: Address)
  {
    // (implicit session: Session) = Address.list
    db.withSession { implicit session =>

    }

  }


}
