package services

import domain.CarePlan
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo
import services.impl.CarePlanServiceImpl

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/23/14.
 */
class CarePlanServiceTest extends FeatureSpec with GivenWhenThen {

  val pat = TableQuery[PatientRepo]
  val co = TableQuery[CoordinatorRepo]
  val care = TableQuery[CarePlanRepo]

  val obj : CarePlanService = new CarePlanServiceImpl

  feature("Implementing Care Plan Service") {
    info("Using Care Plan Service")
    info("Tesing if service works")

    scenario(" Implementing Service ") {
      Given("Given a Connection to the Database Through a Repository")
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        def createPlanTest: Unit ={

          val careplan = CarePlan(1, "Healing", "12/12/2014", "13/12/2014", 5, 1)

          val value = obj.createPlan(careplan)
          println("id" + value)
          assert(care.list.filter(_.planId == value).head.description == "Healing")
        }

        def updateCarePlanTest: Unit ={

          val repoList = care.list
          val repo = repoList.filter(_.planId == 8)
          val careNew = CarePlan(repo.head.planId, "Cleaning house", repo.head.startDate, repo.head.endDate, 2, repo.head.coordinator)
          obj.updateCarePlan(careNew, 8)
          val repoList3 = care.list
          assert(repoList3.filter(_.planId == 8).head.description == "Cleaning house")
        }

        def getPatientTest : Unit ={

          val value = obj.getPatient(8)

          assert(value.firstName == "Bo")
        }

        def getPlanIssuedTest: Unit ={

          val value = obj.getPlanIssued(3)
          assert(value.firstName == "Lou")
        }

        def getVisitTest: Unit ={

          val value = obj.getVisit(1)
          assert(value.patientId == 40)
        }

        info("createPlanTest")
        createPlanTest
        info("updateCarePlanTest")
        updateCarePlanTest
        info("getPatientTest")
        getPatientTest
        info("getPlanIssuedTest")
        getPlanIssuedTest
        info("getVisitTest")
        getVisitTest
      }
    }
  }
}
