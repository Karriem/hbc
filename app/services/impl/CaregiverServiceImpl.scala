package services.impl

import domain.{User, CarePlan, Patient}
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo
import services.{CoordinatorService, CaregiverService}
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/19/14.
 */
class CaregiverServiceImpl extends CaregiverService{

  val careRepo = TableQuery[CarePlanRepo]
  val patRepo = TableQuery[PatientRepo]
  val userRepo = TableQuery[UserRepo]

  override def getCareplan(id: Long): CarePlan = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val careList = careRepo.list

      val careplan = careList.filter(_.planId == id).head
      careplan
    }
  }

  override def getPatientDetails(id: Long): Patient = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patList = patRepo.list

      val patient = patList.filter(_.patientId == id).head
      patient
    }
  }

  override def getUserDetails(id: Long): User =  {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val userList = userRepo.list

      val user = userList.filter(_.caregiverId.getOrElse() == id).head
      user
    }
  }

  override def addPatient(patient: Patient) :Long= {

    val co : CoordinatorService = new CoordinatorServiceImpl

    val value = co.addPatient(patient)
    value
  }
}
