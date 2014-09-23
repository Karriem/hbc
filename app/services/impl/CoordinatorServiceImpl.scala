package services.impl

import domain._
import repository.CaregiverModel.CaregiverRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo
import services.CoordinatorService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/18/14.
 */
class CoordinatorServiceImpl extends CoordinatorService{

  val coorRepo = TableQuery[CoordinatorRepo]
  val insRepo = TableQuery[InstitutionRepo]
  val userRepo = TableQuery[UserRepo]
  val patRepo = TableQuery[PatientRepo]
  val givRepo = TableQuery[CaregiverRepo]

  override def getInstitution(id: Long): Institution = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val insList = insRepo.list

      val coor = insList.filter(_.coordinatorId.get == id).head
      coor
    }
  }

  override def getUser(id: Long): User = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val userList = userRepo.list

      val coor = userList.filter(_.coordinatorId.getOrElse() == id).head
      coor
    }
  }

  override def createUser(user: User): Long=  {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val value = userRepo.returning (userRepo.map (_.userId)).insert(user)
      value
    }
  }

  override def addCoordinator(co: Coordinator): Long={

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val value = coorRepo.returning (coorRepo.map (_.coId)).insert(co)
      value
    }
  }

  override def viewPatients(id: Long): Patient ={

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patList = patRepo.list

      val pat = patList.filter(_.patientId == id).head
      pat
    }
  }

  override def viewAllPatient(): List[PatientRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val patList = patRepo.list
      patList
    }
  }

  override def createCarePlan(care: CarePlan): Long= {

      val careplan = new CarePlanServiceImpl

      val value = careplan.createPlan(care)
    value
  }

  override def addCareGiver(giver: Caregiver): Long= {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val value = givRepo.returning (givRepo.map (_.caregiverId)).insert(giver)
      value
    }
  }

  override def addPatient(pat: Patient): Long = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     val value = patRepo.returning(patRepo.map(_.patientId)).insert(pat)
      value
    }
  }
}
