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

      def getDemo: Unit ={

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
          val value = demoservice.getPersonDemo(9)
          println("Vsalue" + value)
          assert(demoRepo.list.filter(_.caregiverId.getOrElse() == value).head.gender == "Female")

        }
        }
      info("Testing read for a person's demographics")
      getDemo
    }
  }
}
