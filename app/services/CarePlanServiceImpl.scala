package services

import domain.{CarePlan, Patient, Coordinator, Visit}
import repository.CarePlanModel.CarePlanRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/16/14.
 */
class CarePlanServiceImpl extends CarePlanService{

  override def createPlan(care: CarePlan): Unit = {

    val careRepo = TableQuery[CarePlanRepo]

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      careRepo.insert(care)
    }
  }

  override def getPatient(id: Long): Patient = ???

  override def getPlanIssued(id: Long): Coordinator = ???

  override def getVisit(id: Long): Visit = ???
}
