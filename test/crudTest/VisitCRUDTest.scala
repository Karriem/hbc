package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import domain.{TimeSheet, Visit, CarePlan}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.TimeSheetModel.TimeSheetRepo
import repository.VisitModel.VisitRepo

import scala.slick.driver.MySQLDriver.simple._

class VisitCRUDTest  extends FeatureSpec with GivenWhenThen{

  feature("Save Visit") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val visitRepo = TableQuery[VisitRepo]
      val careplanRepo = TableQuery[CarePlanRepo]
      val timesheetRepo = TableQuery[TimeSheetRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(visitRepo.ddl).create
        //(careplanRepo.ddl).create
        info("Creating Visit")
        val carePlanRec = CarePlan(1, "TB Treatment", "2014/08/22" , "2014/09/22" , 0, 0)
        val planID = careplanRepo.returning(careplanRepo .map(_.planId)).insert(carePlanRec)

        val visitRec = Visit(1, "2014/09/12", planID)
        val visitID= visitRepo.returning(visitRepo.map(_.visitId)).insert(visitRec)

        val timeSheetRecord = TimeSheet("2013/12/12", "08:00", "16:00", Some(visitID), Some(0), Some(0))
        timesheetRepo.insert(timeSheetRecord)

        def Read(workDay: String, id: Long) = {
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.workDay == workDay)
            }
          }

        }

        def Update(newWorkDay:String, id:Long) = {
          timesheetRepo.filter(_.visitId === id).map(_.workDay).update(newWorkDay)
          Read(newWorkDay, id)
        }

        /*def searchDelete(id: Long) = {
          pat foreach { case (patient: Patient) =>
            println("/////" + id)
            assertResult(true) {

              if (patient.patientId == id) {
                true
              }
              else
                false
            }
          }
        }*/

        def Delete(id:Long) = {
          timesheetRepo.filter(_.scheduleId=== id).delete
          visitRepo.filter(_.visitId=== id).delete
          //searchDelete(id)
        }

        info("Reading Visit")
        Read("2013/12/12", visitID)

        info("Updating Visit")
        Update("2014/03/23", visitID)

        info("Deleting Visit")
        Delete(visitID)



      }
    }
  }


  }
