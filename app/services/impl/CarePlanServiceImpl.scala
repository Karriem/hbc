package services.impl

import domain.{CarePlan, Coordinator, Patient, Visit}
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

  override def createPlan(care: CarePlan): Long = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val id = careRepo.returning (careRepo.map (_.planId)).insert(care)

      return id
    }
  }

  override def getPatient(id: Long): Patient = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      careRepo foreach { case (careplan: CarePlan) =>
        if (careplan.patientId == id) {
          patRepo foreach { case (patient : Patient) =>
            if (patient.patientId == careplan.patientId){
              return patient
            }
          }
        }
      }
      return null
    }
  }

  override def getPlanIssued(id: Long): Coordinator = ???

  override def getVisit(id: Long): Visit = ???
}
