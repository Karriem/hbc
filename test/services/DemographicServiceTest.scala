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

      def getDemo: Unit = {

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

          val value = demoservice.getPersonDemo(2)
            assert(value.gender == "male")

        }
      }

        def getAllDemos: Unit ={

          Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

            var demolist : List[DemographicRepo#TableElementType] = List()
            demolist = demoservice.getAllDemos()
            assert(demolist.size == 3)
          }
          }


         info("Testing read for a person's demographics")
         getDemo

         info("Testing for get all demos service")
         getAllDemos
    }
  }
}
