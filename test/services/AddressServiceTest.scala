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
      val addressService: AddressService = new AddressServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getCagiverAddress {
          val value = addressService.getCaregiverAddress(141)
          assert(value.streetAddress == "9 Park Avenue")

        }
        def getAllAddresses {
          var addressList: List[AddressRepo#TableElementType] = List()
          addressList = addressService.getAllAddresses()
          assert(addressList.size == 4)

        }

        def getCoordinatorAddress: Unit ={
          val value = addressService.getCoordinatorAddress(111)
          assert(value.postalAddress == "30 Chester Road ")

        }

        def getContactPersonAdd: Unit ={
          val value = addressService.getContactPersonAddress(11)
          assert(value.caregiverId == None)
        }

        def getPatientAddress: Unit ={
          val value = addressService.getPatientAddress(144)
          assert(value.postalAddress == "5 Maynart")
        }

        info("Getting caregiver address")
         getCagiverAddress

        info("Getting all addresses")
          //getAllAddresses

        info("Getting coordinator address")
          getCoordinatorAddress

        info("Getting contact person address")
         getContactPersonAdd

        info("Getting patient address")
         getPatientAddress
      }
    }
  }
}
