package services

import domain._
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

          val value = obj.getInstitution(10)
          assert(value.instituteName == "Grabouw Hospital")
        }

        def viewPatientsTest: Unit ={

          val value = obj.viewPatients(2)
          assert(value.firstName == "Bo")
        }

        def viewAllPatientTest: Unit ={

          var list : List[PatientRepo#TableElementType] = List()

          list = obj.viewAllPatient()
          assert(list.size == 53)
        }

        def createUserTest: Unit ={

          val userRepo = TableQuery[UserRepo]
          val userRecord = User(1, "root", "pass", Some(5), Some(0))
          val value = obj.createUser(userRecord)

          assert(userRepo.list.filter(_.userId == value).head.password == "pass")
        }

        def getUserTest: Unit ={

          val value = obj.getUser(13)
          assert(value.username == "Sasuke")
        }

        def addCoordinatorTest: Unit ={

          val coRepo = TableQuery[CoordinatorRepo]
          val coordinatorRecord = Coordinator(1,  "Nikki", "Shiyagaya")
          val value = obj.addCoordinator(coordinatorRecord)

          assert(coRepo.list.filter(_.coId == value).head.firstName == "Nikki")
        }

        def createCarePlanTest: Unit ={

          val careRepo = TableQuery[CarePlanRepo]
          val careplan = CarePlan(1, "Caring for elder", "5/05/2014", "5/05/2014", 5, 1)
          val value = obj.createCarePlan(careplan)

          assert(careRepo.list.filter(_.planId == value).head.description == "Caring for elder")
        }

        def addCareGiverTest: Unit ={

          val giverRepo = TableQuery[CaregiverRepo]
          val giver = Caregiver(1, "L", "Desu")
          val value = obj.addCareGiver(giver)

          assert(giverRepo.list.filter(_.caregiverId == value).head.firstName == "L")
        }

        def addPatientTest: Unit ={

          val patRepo = TableQuery[PatientRepo]
          val patRecord = Patient(1, "20/05/2014", "2/08/2014", "TYes", "a")
          val value = obj.addPatient(patRecord)

          assert(patRepo.list.filter(_.patientId == value).head.firstName == "TYes")
        }

        info("getInstitutionTest")
        getInstitutionTest
        info("viewPatientTest")
        viewPatientsTest
        info("viewAllPatientsTest")
        //viewAllPatientTest
        info("getUserTest")
        getUserTest
        info("createUserTest")
        createUserTest
        info("addCoordinatorTest")
        addCoordinatorTest
        info("createCarePlanTest")
        createCarePlanTest
        info("addCaregiverTest")
        addCareGiverTest
        info("addPatientTest")
        addPatientTest
      }
    }
  }
}
