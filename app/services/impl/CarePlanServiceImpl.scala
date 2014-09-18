package services.impl

import domain.{CarePlan, Coordinator, Visit}
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import services.CarePlanService

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/16/14.
 */
class CarePlanServiceImpl extends CarePlanService{

  val careRepo = TableQuery[CarePlanRepo]
  val patRepo = TableQuery[PatientRepo]

  override def createPlan(care: CarePlan) {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      careRepo.insert(care)
    }
  }
  override def getPatient(id: Long) {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patId = careRepo.list
      val patient = patRepo.list
      val listP = patId.filter(_.planId == id).map(_.patientId)

      val listPat = patient.filter(_.patientId == listP.head)
      println("List: " +listPat.head.LastName)

    }
  }

  override def getPlanIssued(id: Long): Coordinator = ???

  override def getVisit(id: Long): Visit = ???
}
