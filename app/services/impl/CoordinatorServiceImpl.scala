package services.impl

import domain.{User, Coordinator}
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

  //val coorList = coorRepo.list

  //val userList = userRepo.list
  //val patList = patRepo.list

  override def getInstitution(id: Long): Unit = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val insList = insRepo.list

      val coor = insList.filter(_.coordinatorId.get == id)
      println("Institution Name: " +coor.head.instituteName)
    }
  }

  override def getUser(id: Long): Unit = ???

  override def createUser(user: User): Unit = ???

  override def addCoordinator(co: Coordinator): Unit = ???

  override def viewPatients(id: Long): Unit = ???
}
