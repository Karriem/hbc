package services.impl

import domain.{Coordinator, Patient, CarePlan}
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

  override def createPlan(care: CarePlan) :Long= {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val value = careRepo.returning (careRepo.map (_.planId)).insert(care)
      value
    }
  }

  override def getPatient(id: Long): Patient = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patId = careRepo.list
      val patient = patRepo.list

      val listP = patId.filter(_.planId == id).map(_.patientId)
      val listPat = patient.filter(_.patientId == listP.head)
      listPat.head
    }
  }

  override def getPlanIssued(id: Long): Coordinator = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coId = careRepo.list
      val pat = coRepo.list

      val coorID = coId.filter(_.planId == id).map(_.coordinator)
      val coor = pat.filter(_.coId == coorID.head).head
      coor
    }
  }

  override def getVisit(id: Long): CarePlan = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val coId = careRepo.list

      val visList = visitRepo.list

      val visId = visList.filter(_.visitId == id).map(_.carePlanId)
      val coor = coId.filter(_.planId == visId.head).head
      coor
    }
  }

  override def updateCarePlan(care: CarePlan, id : Long): Unit = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //val careList = careRepo.list

      careRepo.filter(_.planId === id).update(care)
    }
  }

  override def getCarePlan(id: Long): CarePlan = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val careList = careRepo.list

      val care = careList.filter(_.planId == id)

      care.head
    }
  }
}
