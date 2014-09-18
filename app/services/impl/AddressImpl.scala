package services.impl

import anorm.Sql
import domain.Address
import repository.AddressModel.AddressRepo
import services.AddressService
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by phakama on 2014/09/18.
 */
class AddressImpl extends AddressService {
  val db = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin")//.withSession { implicit session =>
    val addressRepo = TableQuery[AddressRepo]

    override def createAddress(address: Address): Unit = {
      db.withSession { implicit session =>
      addressRepo.insert(address)

    }
  }
  override def getAddressById(id: Long): Unit =
  {
    val db = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      // db.withSession {implicit session =>
      addressRepo.firstOption
      addressRepo.filter(_.postalAddress === "30 Chester Road")
      addressRepo.length.run
      println("Adress: "+ addressRepo)

      /*  val addressById = address.list

        val listAd = addressById.filter(_.caregiverId == id).map(_.caregiverId)

        val listAdress = addressById.filter(_.caregiverId == listAd.head)
        println("Address: " + listAdress)*/

    }


  }

    override def getAllAddresses(addresses: Address): Unit =
    {
      // (implicit session: Session) = Address.list
      db.withSession { implicit session =>

      }

      }

  
}
