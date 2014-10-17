package services

import org.scalatest.{FeatureSpec, GivenWhenThen}
import services.impl.CaregiverServiceImpl

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by karriem on 9/23/14.
 */
class CaregiverServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Implementing Caregiver Service") {
    info("Using Caregiver Service")
    info("Tesing if service works")

    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val careObj : CaregiverService = new CaregiverServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getCarePlanTest ={

          val obj = careObj.getCareplan(124)
          assert(obj.description == "Cleaning house")
        }

        def getPatientDetailsTest = {

          val obj = careObj.getPatientDetails(128)
          assert(obj.firstName == "Phakama")
        }

        def getUserDetailsTest ={

          val obj = careObj.getUserDetails(138)
          assert(obj.username == "root")
        }

        info("GetCarePlanTest")
        getCarePlanTest
        info("GetPatientDetailsTest")
        getPatientDetailsTest
        info("GetUserDetailsTest")
        getUserDetailsTest
      }
    }
  }
}
