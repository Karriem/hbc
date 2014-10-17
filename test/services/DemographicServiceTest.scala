package services

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.DemographicModel.DemographicRepo
import services.impl.DemographicServiceImpl

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by phakama on 2014/09/25.
 */
class DemographicServiceTest extends FeatureSpec with GivenWhenThen{
  feature("Implementing Patient Service") {
    info("Using Patient Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val demoservice: DemographicService = new DemographicServiceImpl
      val demoRepo = TableQuery[DemographicRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        def getPersonDemo: Unit = {

          val value = demoservice.getPersonDemo(11)
          assert(value.gender == "Female")
        }

        def getCaregiverDemo: Unit = {

          val value = demoservice.getCaregiverDemo(141)
          assert(value.age == 45)
        }

        def getCoordinatorDemo: Unit ={

          val value = demoservice.getCoordinatorDemo(109)
          assert(value.gender == "Male")
        }

        def getPatientDemo: Unit ={

          val value = demoservice.getPatientDemo(132)
          assert(value.age == 25)
        }

        def getAllDemos: Unit = {

          var demolist: List[DemographicRepo#TableElementType] = List()
          demolist = demoservice.getAllDemos()
          assert(demolist.size == 4)
        }


        info("Testing read for a person's demographics")
        getPersonDemo

        info("Testing for getting all demos service")
        //getAllDemos

        info("Testing for get caregiver demos service")
        getCaregiverDemo

        info("Testing for get coordinator demos service")
        getCoordinatorDemo

        info("Testing for get patient demos service")
        getPatientDemo
      }
    }
  }
}
