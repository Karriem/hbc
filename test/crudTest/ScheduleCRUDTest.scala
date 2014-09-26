package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import java.util.Date

import domain.{TimeSheet, Schedule, Patient, Caregiver}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.PatientModel.PatientRepo
import repository.ScheduleModel.ScheduleRepo
import repository.TimeSheetModel.TimeSheetRepo

import scala.slick.driver.MySQLDriver.simple._

class ScheduleCRUDTest extends FeatureSpec with GivenWhenThen{
  feature("Save User") {
    info("As Administrator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val caregiverRepo = TableQuery[CaregiverRepo]
      val patientRepo = TableQuery[PatientRepo]
      val timesheetRepo = TableQuery[TimeSheetRepo]
      val scheduleRepo = TableQuery[ScheduleRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

       // (timesheetRepo.ddl).create
        //(scheduleRepo.ddl).create
       // (patientRepo.ddl).create

        val workDay = new DateTime(2013, 12, 12, 0, 0)
        val timeIn = new DateTime(2013, 12, 12, 8, 0)
        val timeOut = new DateTime(2013, 12, 12, 16, 0)
        val upDatedWorkDay = new DateTime(2013, 3, 23, 0 ,0 )

        info("Creating Schedule")
        val caregiverRecord = Caregiver(1, "Max", "Crews")
        val careId = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiverRecord)

        val patientRecord = Patient(1, "2013/03/12" , "2014/04/24",  "Tonata", "Nakashololo")
        val patId = patientRepo.returning(patientRepo.map(_.patientId)).insert(patientRecord)

        val scheduleRecord = Schedule(1, patId, careId)
        val scheduleId = scheduleRepo.returning(scheduleRepo.map(_.scheduleId)).insert(scheduleRecord)

        val timeSheetRecord = TimeSheet(workDay.toDate, timeIn.toDate, timeOut.toDate, Some(0), Some(0), Some(scheduleId))
        timesheetRepo.insert(timeSheetRecord)

        def Read(workDay: Date, id: Long) = {
          timesheetRepo foreach { case (sheet: TimeSheet) =>
            if (sheet.scheduleId == Option(id)) {
              assert(sheet.workDay == workDay)
            }
            }

          }

        def Update(newWorkDay:Date, id:Long) = {
          timesheetRepo.filter(_.scheduleId === id).map(_.workDay).update(newWorkDay)
          Read(newWorkDay, id)
        }

        def searchDelete(id: Long) : Int = {
          scheduleRepo foreach { case (cr: Schedule) =>
            assertResult(false) {
              scheduleRepo.filter(_.scheduleId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          timesheetRepo.filter(_.scheduleId=== id).delete
          scheduleRepo.filter(_.scheduleId=== id).delete
          searchDelete(id)
        }

        info("Reading Schedule")
        Read(workDay.toDate, scheduleId)

        info("Updating Schedule")
        Update(upDatedWorkDay.toDate, scheduleId)

        info("Deleting Schedule")
        Delete(scheduleId)

        }


      }
    }
  }




