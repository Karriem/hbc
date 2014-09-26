package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import java.util.Date

import domain._
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CarePlanModel.CarePlanRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo
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
      val coordinatorRepo = TableQuery[CoordinatorRepo]
      val patRepo = TableQuery[PatientRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(visitRepo.ddl).create
        //(careplanRepo.ddl).create

        info("Creating Visit")
        val wd = new DateTime(2013, 12, 12, 0 ,0)
        val ti = new DateTime(2013, 12, 12, 8, 0)
        val to = new DateTime(2013, 12, 12, 16, 0 )

        val upWd =  new DateTime(2014, 3, 23, 0 , 0)


        val coordinator = Coordinator(1,  "Nikki", "Shiyagaya")
        val coID = coordinatorRepo.returning(coordinatorRepo.map(_.coId)).insert(coordinator)

        val patRecord = Patient(1, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "tonata", "nak")
        val patID = patRepo.returning(patRepo.map(_.patientId)).insert(patRecord)

        val carePlanRec = CarePlan(1, "TB Treatment", DateTime.parse("2014-08-22").toDate , DateTime.parse("2014-09-22").toDate , patID, coID)
        val planID = careplanRepo.returning(careplanRepo .map(_.planId)).insert(carePlanRec)

        val visitRec = Visit(1, DateTime.parse("2014-09-12").toDate, planID)
        val visitID= visitRepo.returning(visitRepo.map(_.visitId)).insert(visitRec)

        val timeSheetRecord = TimeSheet(wd.toDate, ti.toDate, to.toDate , Some(visitID), Some(0), Some(0))
        timesheetRepo.insert(timeSheetRecord)

        def Read(timeIn: Date, id: Long) = {
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.visitId == Option(id)) {
              assert(sheet.timeIn == timeIn)
            }
          }
        }

        def Update(newWorkDay:Date, id:Long) = {
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
        Read(ti.toDate, visitID)

        info("Updating Visit")
        Update(upWd.toDate, visitID)

        info("Deleting Visit")
        Delete(visitID)
      }
    }
  }


  }
