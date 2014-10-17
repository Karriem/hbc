package services.impl

import domain._
import repository.AddressModel.AddressRepo
import repository.AdherenceModel.AdherenceRepo
import repository.CarePlanModel.CarePlanRepo
import repository.CaregiverModel.CaregiverRepo
import repository.ContactModel.ContactRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.DemographicModel.DemographicRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo
import services.CoordinatorService
import scala.slick.driver.MySQLDriver.simple._
import org.mindrot.jbcrypt.BCrypt

/**
 * Created by karriem on 9/18/14.
 */
class CoordinatorServiceImpl extends CoordinatorService{

  val coorRepo = TableQuery[CoordinatorRepo]
  val insRepo = TableQuery[InstitutionRepo]
  val userRepo = TableQuery[UserRepo]
  val patRepo = TableQuery[PatientRepo]
  val givRepo = TableQuery[CaregiverRepo]
  val careRepo = TableQuery[CarePlanRepo]
  val demoRepo = TableQuery[DemographicRepo]
  val contactRepo = TableQuery[ContactRepo]
  val addressRepo = TableQuery[AddressRepo]
  val adRepo = TableQuery[AdherenceRepo]

  val dataCon = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin")

  override def getInstitution(id: Long): Institution = {

    dataCon.withSession { implicit session =>

      val insList = insRepo.list

      val coor = insList.filter(_.coordinatorId.get == id).head
      coor
    }
  }

  override def checkCredentials(username:String, password:String): String = {

    dataCon.withSession { implicit session =>

      var id = 1L
      val userList = userRepo.list
      //val encryptPass = BCrypt.hashpw(password, BCrypt.gensalt())

      userList foreach { case (u: User) =>
        if (u.username == username && BCrypt.checkpw(password, u.password)) {
          id = u.userId
        }
      }
      id.toString
    }
  }

  override def createUser(user: User): Long=  {

    dataCon.withSession { implicit session =>

      val list = userRepo.list

      var value = 1L

      list foreach { case (u :User) =>

          if (u.username == user.username){
                   value = 0L
          }
      }

      if (value == 1) {
        val encryptPass = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val newUser = User(user.userId, user.username, encryptPass, user.caregiverId, user.coordinatorId)

        value = userRepo.returning(userRepo.map(_.userId)).insert(newUser)
      }
      value
    }
  }

  override def addCoordinator(co: Coordinator): Long={

    dataCon.withSession { implicit session =>

      val value = coorRepo.returning (coorRepo.map (_.coId)).insert(co)
      value
    }
  }

  override def viewPatients(id: Long): Patient ={

    dataCon.withSession { implicit session =>

      val patList = patRepo.list

      val pat = patList.filter(_.patientId == id).head
      pat
    }
  }

  override def viewAllPatient(): List[PatientRepo#TableElementType] = {

    dataCon.withSession { implicit session =>

      val patList = patRepo.list
      patList
    }
  }

  override def createCarePlan(care: CarePlan): Long= {

      val careplan = new CarePlanServiceImpl

      val value = careplan.createPlan(care)
    value
  }

  override def addCaregiver(giver: Caregiver): Long= {

    dataCon.withSession { implicit session =>

      val value = givRepo.returning (givRepo.map (_.caregiverId)).insert(giver)
      value
    }
  }

  override def addPatient(pat: Patient): Long = {

    dataCon.withSession { implicit session =>

      val value = patRepo.returning(patRepo.map(_.patientId)).insert(pat)
      value
    }
  }

  override def updateCoordinator(co: Coordinator, id: Long): Long = {

    dataCon.withSession { implicit session =>

      coorRepo.filter(_.coId === id).update(co)
      id
    }
  }

  override def deletePatient(id: Long): Unit = {

    dataCon.withSession { implicit session =>

      adRepo.filter(_.patientId === id).delete
      patRepo.filter(_.patientId === id).delete
    }
  }

  override def deleteUser(id: Long): Unit = {

    dataCon.withSession { implicit session =>

      givRepo.filter(_.caregiverId === id ).delete
      userRepo.filter(_.userId === id).delete
    }
  }

  override def getCaregiver(id: Long): Caregiver = {

    dataCon.withSession { implicit session =>

      val giverList = givRepo.list

      val giver = giverList.filter(_.caregiverId == id).head
      giver
    }
  }

  override def updateCarePlan(care: CarePlan, id: Long): Long = {

    dataCon.withSession { implicit session =>

      careRepo.filter(_.planId === id).update(care)
      id
    }
  }

  override def updateCaregiver(giver: Caregiver, id: Long): Long = {

    dataCon.withSession { implicit session =>

      givRepo.filter(_.caregiverId === id).update(giver)
      id
    }
  }

  override def deleteCaregiver(id: Long): Unit = {

    dataCon.withSession { implicit session =>

      demoRepo.filter(_.caregiverId === id).delete
      contactRepo.filter(_.caregiverId === id).delete
      addressRepo.filter(_.caregiverId === id).delete
      givRepo.filter(_.caregiverId === id).delete
    }
  }

  override def updatePatient(pat: Patient, id: Long): Long = {

    dataCon.withSession { implicit session =>

      patRepo.filter(_.patientId === id).update(pat)
      id
    }
  }

  override def updateUser(user: User, id: Long): Long = {

    dataCon.withSession { implicit session =>

      userRepo.filter(_.userId === id).update(user)
      id
    }
  }

  override def deleteCoordinator(id: Long): Unit = {

    dataCon.withSession { implicit session =>

      demoRepo.filter(_.coordinatorId === id).delete
      contactRepo.filter(_.coordinatorId === id).delete
      addressRepo.filter(_.coordinatorId === id).delete
      coorRepo .filter(_.coId === id).delete
    }
  }

  override def deleteCarePlan(id: Long): Unit = {

    dataCon.withSession { implicit session =>

      careRepo.filter(_.planId === id).delete
    }
  }

  override def getUser(id: String): String = {

    dataCon.withSession { implicit session =>

      val userList = userRepo.list

      val coor = userList.filter(_.userId == id.toLong).map(_.username).head
      coor
    }
  }
}
