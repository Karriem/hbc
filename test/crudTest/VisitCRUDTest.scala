package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import domain.{CarePlan, TimeSheet, Visit}
import org.joda.time.DateTime
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
        val carePlanRec = CarePlan(1, "TB Treatment", DateTime.parse("2014-08-22").toDate , DateTime.parse("2014-09-22").toDate , 0, 0)
        val planID = careplanRepo.returning(careplanRepo .map(_.planId)).insert(carePlanRec)

        val visitRec = Visit(1, DateTime.parse("2014-09-12").toDate, planID)
        val visitID= visitRepo.returning(visitRepo.map(_.visitId)).insert(visitRec)

        val timeSheetRecord = TimeSheet("2013-12-12", "08:00:00", "16:00:00", Some(visitID), Some(0), Some(0))
        timesheetRepo.insert(timeSheetRecord)

        def Read(timeIn: String, id: Long) = {
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.timeIn == timeIn)
            }
          }
        }

        def Update(newWorkDay:String, id:Long) = {
          timesheetRepo.filter(_.visitId === id).map(_.workDay).update(newWorkDay)
          //Read(newWorkDay, id)
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.workDay == newWorkDay)
            }
          }
        }

        def searchDelete(id: Long) : Int = {
          visitRepo foreach { case (cr: Visit) =>
            assertResult(false) {
              visitRepo.filter(_.visitId === id).exists.run
            }
          }

          return 0;
        }

        def Delete(id:Long) = {
          timesheetRepo.filter(_.scheduleId=== id).delete
          visitRepo.filter(_.visitId === id).delete
          searchDelete(id)
        }

        info("Reading Visit")
        Read("08:00:00", visitID)

        info("Updating Visit")
        Update("2014-03-23", visitID)

        info("Deleting Visit")
        Delete(visitID)
      }
    }
  }


  }
