package services

import org.scalatest.{GivenWhenThen, FeatureSpec}
import services.impl.ContactServiceImpl
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/10/08.
 */
class ContactServiceTest extends FeatureSpec with GivenWhenThen{
  feature("Implementing Contact person Service") {
    info("Using ContactPerson Service")
    info("Tesing if service works")
    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")

      val contactservice: ContactService = new ContactServiceImpl

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


      }
      }
  }

}
