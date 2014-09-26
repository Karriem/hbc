package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import java.util.Date

import domain.{TimeSheet, Visit, CarePlan}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.TimeSheetModel.TimeSheetRepo
import repository.VisitModel.VisitRepo

import scala.slick.driver.MySQLDriver.simple._

class VisitCRUDTest  extends FeatureSpec with GivenWhenThen {

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
        val workDay = new DateTime(2013, 12, 12, 0, 0)
        val timeIn = new DateTime(2013, 12, 12, 8, 0)
        val timeOut = new DateTime(2013, 12, 12, 16, 0)
        val upDatedWorkDay = new DateTime(2013, 4, 23, 0, 0)

        info("Creating Visit")
        val carePlanRec = CarePlan(1, "TB Treatment", "2014/08/22", "2014/09/22", 0, 0)
        val planID = careplanRepo.returning(careplanRepo.map(_.planId)).insert(carePlanRec)

        val visitRec = Visit(1, "2014/09/12", planID)
        val visitID = visitRepo.returning(visitRepo.map(_.visitId)).insert(visitRec)

        val timeSheetRecord = TimeSheet(workDay.toDate, timeIn.toDate, timeOut.toDate, Some(visitID), Some(0), Some(0))
        timesheetRepo.insert(timeSheetRecord)

        def Read(timeIn: Date, id: Long) = {
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.timeIn == timeIn)
            }
          }
        }

        def Update(newWorkDay: Date, id: Long) = {
          timesheetRepo.filter(_.visitId === id).map(_.workDay).update(newWorkDay)
          //Read(newWorkDay, id)
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.workDay == newWorkDay)
            }
          }
        }

        def searchDelete(id: Long): Int = {
          visitRepo foreach { case (cr: Visit) =>
            assertResult(false) {
              visitRepo.filter(_.visitId === id).exists.run
            }
          }

          return 0;
        }

        def Delete(id: Long) = {
          timesheetRepo.filter(_.scheduleId === id).delete
          visitRepo.filter(_.visitId === id).delete
          searchDelete(id)
        }

        info("Reading Visit")
        Read(timeIn.toDate, visitID)

        info("Updating Visit")
        Update(upDatedWorkDay.toDate, visitID)

        info("Deleting Visit")
        Delete(visitID)

      }
    }
  }

}



