package services.impl

import domain.CarePlan
import repository.CarePlanModel.CarePlanRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo
import repository.VisitModel.VisitRepo
import services.CarePlanService

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/16/14.
 */
class CarePlanServiceImpl extends CarePlanService{

  val careRepo = TableQuery[CarePlanRepo]
  val patRepo = TableQuery[PatientRepo]
  val coRepo = TableQuery[CoordinatorRepo]
  val visitRepo = TableQuery[VisitRepo]

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
      println("Patient Last Name: " +listPat.head.LastName)
    }
  }

  override def getPlanIssued(id: Long) {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coId = careRepo.list
      val pat = coRepo.list

      val coorID = coId.filter(_.planId == id).map(_.coordinator)
      val coor = pat.filter(_.coId == coorID.head)
      println("Coordinator Name: " +coor.head.firstName)
    }
  }

  override def getVisit(id: Long) {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coId = careRepo.list

      val visList = visitRepo.list

      val visId = visList.filter(_.visitId == id).map(_.carePlanId)
      val coor = coId.filter(_.planId == visId.head)
      println("Visit Description: " +coor.head.description)
    }
  }
}
