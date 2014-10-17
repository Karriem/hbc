package services

import domain._
import org.joda.time.DateTime
import org.mindrot.jbcrypt.BCrypt
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.CaregiverModel.CaregiverRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo
import services.impl.CoordinatorServiceImpl

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by karriem on 9/23/14.
 */
class CoordinatorServiceTest extends FeatureSpec with GivenWhenThen {

  val obj : CoordinatorService = new CoordinatorServiceImpl

  feature("Implementing Care Plan Service") {
    info("Using Care Plan Service")
    info("Tesing if service works")

    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def getInstitutionTest: Unit ={

          val value = obj.getInstitution(111)
          assert(value.instituteName == "Parow Hospital")
        }

        def viewPatientsTest: Unit ={

          val value = obj.viewPatients(132)
          assert(value.firstName == "Bo")
        }

        def viewAllPatientTest: Unit ={

          var list : List[PatientRepo#TableElementType] = List()

          list = obj.viewAllPatient()
          assert(list.size == 255)
        }

        def createUserTest: Unit ={

          val userRepo = TableQuery[UserRepo]
          val userRecord = User(1, "Admin", "admin", Some(5), None)
          val value = obj.createUser(userRecord)

          val encryptPass = BCrypt.hashpw(userRecord.password, BCrypt.gensalt())
          assert(BCrypt.checkpw("admin", encryptPass) == true)
        }

        def getUserTest: Unit ={

          val value = obj.getUser(obj.checkCredentials("Admin", "admin"))
          assert(value == "Admin")
        }

        def checkUserDetails: Unit = {

          val value = obj.checkCredentials("Admin", "admin")
          assert(value == 258.toString)
        }

        def addCoordinatorTest: Unit ={

          val coRepo = TableQuery[CoordinatorRepo]
          val coordinatorRecord = Coordinator(1,  "Nikki", "Shiyagaya")
          val value = obj.addCoordinator(coordinatorRecord)

          assert(coRepo.list.filter(_.coId == value).head.firstName == "Nikki")
        }

        def createCarePlanTest: Unit ={

          val careRepo = TableQuery[CarePlanRepo]
          val careplan = CarePlan(1, "Caring for elder", DateTime.parse("2014-05-05").toDate, DateTime.parse("2014-05-05").toDate, 5, 1)
          val value = obj.createCarePlan(careplan)

          assert(careRepo.list.filter(_.planId == value).head.description == "Caring for elder")
        }

        def addCaregiverTest: Unit ={

          val giverRepo = TableQuery[CaregiverRepo]
          val giver = Caregiver(1, "L", "Desu")
          val value = obj.addCaregiver(giver)

          assert(giverRepo.list.filter(_.caregiverId == value).head.firstName == "L")
        }

        def addPatientTest: Unit ={

          val patRepo = TableQuery[PatientRepo]
          val patRecord = Patient(1, DateTime.parse("2014-08-05").toDate, DateTime.parse("2014-08-2").toDate, "TYes", "a")
          val value = obj.addPatient(patRecord)

          assert(patRepo.list.filter(_.patientId == value).head.firstName == "TYes")
        }

        def updateCoordinatorTest: Unit = {

          val coorRepo = TableQuery[CoordinatorRepo]

          val coObj = Coordinator(175, "Bob", "Lord")
          obj.updateCoordinator(coObj, 175)
        }

        info("getInstitutionTest")
        //getInstitutionTest
        info("viewPatientTest")
        //viewPatientsTest
        info("viewAllPatientsTest")
        //viewAllPatientTest
        info("checkUserDetails")
        checkUserDetails
        info("getUserTest")
        getUserTest
        info("createUserTest")
        //createUserTest
        info("addCoordinatorTest")
        //addCoordinatorTest
        info("createCarePlanTest")
        //createCarePlanTest
        info("addCaregiverTest")
        //addCaregiverTest
        info("addPatientTest")
        //addPatientTest
        info("updateCoordinatorTest")
        //updateCoordinatorTest
      }
    }
  }
}
