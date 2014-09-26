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

          val careplan = CarePlan(1, "Healing", DateTime.parse("2014-12-12").toDate, DateTime.parse("2014-12-13").toDate , 5, 1)

          val value = obj.createPlan(careplan)
          println("id" + value)
          assert(care.list.filter(_.planId == value).head.description == "Healing")
        }

        def updateCarePlanTest: Unit ={

          val repoList = care.list
          val repo = repoList.filter(_.planId == 124)
          val careNew = CarePlan(repo.head.planId, "Cleaning house", repo.head.startDate, repo.head.endDate, 2, repo.head.coordinator)
          obj.updateCarePlan(careNew, 124)
          val repoList3 = care.list
          assert(repoList3.filter(_.planId == 124).head.description == "Cleaning house")
        }

        def getPatientTest : Unit ={

          val value = obj.getPatient(128)

          assert(value.firstName == "Phakama")
        }

        def getPlanIssuedTest: Unit ={

          val value = obj.getPlanIssued(124)
          assert(value.firstName == "Lou")
        }

        def getVisitTest: Unit ={

          val value = obj.getVisit(20)
          assert(value.patientId == 0)
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
