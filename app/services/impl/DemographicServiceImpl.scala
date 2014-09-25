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
  val patientRepo = TableQuery[PatientRepo]
  val demoRepo = TableQuery[DemographicRepo]
  val caregiverRepo = TableQuery[CaregiverRepo]


  override def getPersonDemo(id: Long): Demographic = {//List[DemographicRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patientDemo = demoRepo.list
      val patDemoList = patientDemo.filter(_.patientId == id)

      val caregiver = caregiverRepo.list
      val caregiverDemo = caregiver.filter(_.caregiverId == id)

      println("Displaying the person demographics" + patDemoList.head)
      patDemoList.head

    }

  }

  override def getAllDemos(): List[DemographicRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val demoList = demoRepo.list

      println("Geting all the demographics")
      demoList
    }
  }
}
