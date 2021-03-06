package services

import domain.CarePlan
import org.joda.time.DateTime
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

          val careplan = CarePlan(1, "Healing", DateTime.parse("2014-12-12").toDate, DateTime.parse("2014-12-13").toDate , 5, 1, "Death", 250)

          val value = obj.createPlan(careplan)

          assert(care.list.filter(_.planId == value).head.intervention == "Healing")
        }

        def getCarePlanTest: Unit = {

          val value = obj.getCarePlan(3)

          assert(value.patientId == 3)
        }

        def updateCarePlanTest: Unit ={

          val repoList = care.list
          val repo = repoList.filter(_.planId == 5)
          val careNew = CarePlan(repo.head.planId, "Cleaning house", repo.head.startDate, repo.head.endDate, 2, repo.head.coordinator, "Death", 250)
          obj.updateCarePlan(careNew, 5)
          val repoList3 = care.list
          assert(repoList3.filter(_.planId == 5).head.intervention == "Cleaning house")
        }

        def getPatientTest : Unit ={

          val value = obj.getPatient(5)

          assert(value.firstName == "Bo")
        }

        def getPlanIssuedTest: Unit ={

          val value = obj.getPlanIssued(4)
          assert(value.firstName == "Nikki")
        }

        def getVisitTest: Unit ={

          val value = obj.getVisit(1)
          assert(value.patientId == 2)
        }

        info("createPlanTest")
        createPlanTest
        info("getCarePlanTest")
        getCarePlanTest
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
