package services.impl

import domain.{CarePlan, Patient, User}
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo
import services.CaregiverService

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/19/14.
 */
class CaregiverServiceImpl extends CaregiverService{

  val careRepo = TableQuery[CarePlanRepo]
  val patRepo = TableQuery[PatientRepo]
  val userRepo = TableQuery[UserRepo]

  val dataCon = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin")

  override def getCareplan(id: Long): CarePlan = {

    dataCon.withSession { implicit session =>

      val careList = careRepo.list

      val careplan = careList.filter(_.planId == id).head
      careplan
    }
  }

  override def getPatientDetails(id: Long): Patient = {

    dataCon.withSession { implicit session =>

      val patList = patRepo.list
      val careList = careRepo.list

      val careplan = careList.filter(_.planId == id).map(_.patientId).head
      val patient = patList.filter(_.patientId == careplan).head
      patient
    }
  }

  override def getUserDetails(id: Long): User =  {

    dataCon.withSession { implicit session =>

      val userList = userRepo.list

      val user = userList.filter(_.caregiverId.getOrElse() == id).head
      user
    }
  }
}
