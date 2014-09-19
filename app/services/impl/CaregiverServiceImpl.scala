package services.impl

import domain.Patient
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

  override def getCareplan(id: Long): Unit = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val careList = careRepo.list

      val careplan = careList.filter(_.planId == id)
      println("Care Plan Description: " +careplan.head.description)
    }
  }

  override def getPatientDetails(id: Long): Unit = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patList = patRepo.list

      val patient = patList.filter(_.patientId == id)
      println("Patient Name: " +patient.head.firstName)
    }
  }

  override def getUserDetails(id: Long): Unit = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val userList = userRepo.list

      val user = userList.filter(_.caregiverId.get == id)
    }
  }

  override def addPatient(patient: Patient): Unit = {

    val co : CoordinatorService = new CoordinatorServiceImpl

    co.addPatient(patient)
  }
}
