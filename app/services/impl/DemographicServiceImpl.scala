package services.impl

import domain.Demographic
import repository.CaregiverModel.CaregiverRepo
import repository.DemographicModel.DemographicRepo
import repository.PatientModel.PatientRepo
import services.DemographicService

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/09/23.
 */
class DemographicServiceImpl extends DemographicService{

  val demoRepo = TableQuery[DemographicRepo]

  override def getPersonDemo(id: Long): Demographic={//List[DemographicRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val personDemo = demoRepo.filter(_.personId === id).list

      println("Displaying the person demographics" + personDemo)
      personDemo.head

    }

  }

  override def getAllDemos(): List[DemographicRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val demoList = demoRepo.list

      println("Geting all the demographics")
      demoList
    }
  }

  override def getPatientDemo(id: Long): Demographic = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patientDemo = demoRepo.filter(_.patientId === id).list
      patientDemo.head
    }
  }

  override def getCaregiverDemo(id: Long): Demographic = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val caregiverDemo = demoRepo.filter(_.caregiverId === id).list
      caregiverDemo.head
    }
  }

  override def getCoordinatorDemo(id: Long): Demographic = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coordDemo = demoRepo.filter(_.coordinatorId === id).list
      coordDemo.head
    }
  }
}
