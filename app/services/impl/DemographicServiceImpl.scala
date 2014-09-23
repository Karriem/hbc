package services.impl

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

  override def getPersonDemo(id: Long): List[DemographicRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patientDemo = demoRepo.list
      val patDemoList = patientDemo.filter(_.patientId == id)

      println("Displaying the person demographics" + patDemoList)
      patDemoList

    }

  }

  override def getAllDemos(): List[DemographicRepo#TableElementType] = ???
}
