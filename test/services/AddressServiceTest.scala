package services

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.AddressModel.AddressRepo
import services.impl.AddressServiceImpl
import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by phakama on 2014/09/25.
 */
class AddressServiceTest extends FeatureSpec with GivenWhenThen{

  feature("Implementing Address Service") {
    info("Using Address Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val addressRepo = TableQuery[AddressRepo]
      val addressService : AddressService = new AddressServiceImpl

    def getAddress {

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        val value = addressService.getAddressById(138)
        assert(value.streetAddress == "402 Apple Street")

      }
    }
      def getAddresses{

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

          var addressList : List[AddressRepo#TableElementType] = List()
          addressList = addressService.getAllAddresses()
          assert(addressList.size == 6)
        }

        }
      info("Getting an address")
      getAddress
      info("Getting all addresses")
      getAddresses

    }
    }
}
