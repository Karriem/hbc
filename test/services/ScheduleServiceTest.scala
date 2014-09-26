package services

import domain.{TimeSheet, Patient, Schedule, Caregiver}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.PatientModel.PatientRepo
import repository.ScheduleModel.ScheduleRepo
import repository.TimeSheetModel.TimeSheetRepo
import services.impl.ScheduleServiceImpl

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/23/14.
 */
class ScheduleServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Schedule Service") {
    info("I want to carry out specific schedule services")

    scenario("Creating object instances") {
      Given("Specific entity information")

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val schedule = Schedule(1L, 1L, 1L)

        val caregiver = Caregiver(1L, "Tonata", "Nakashololo")

        val patient = Patient(1L, "2013/12/2", "2014/04/12", "Helvi", "Kalenga")

        val timesheet = TimeSheet("2014/09/20", "08:30", "12:30", None, None, Option(1L))

        val scheduleService : ScheduleService = new ScheduleServiceImpl()

        val scheduleRepo = TableQuery[ScheduleRepo]
        val caregiverRepo = TableQuery[CaregiverRepo]
        val patientRepo = TableQuery[PatientRepo]
        val timesheetRepo = TableQuery[TimeSheetRepo]

        var id: Long = 0L
        var careID: Long = 0L
        var patID: Long = 0L

        def testCreateSchedule = {
          id = scheduleService.createSchedule(schedule, caregiver, patient, timesheet)
          scheduleRepo foreach {case (s:Schedule) =>
            if(s.scheduleId == id){
              careID = s.caregiverId
              patID = s.patientId
              caregiverRepo foreach {case (c: Caregiver) =>
                if(c.caregiverId == careID){
                  assert(c.firstName == "Tonata")
                }
              }
            }
          }
        }

        def testGetTimesheetDetails = {
          val timeSheet = scheduleService.getTimesheetDetails(id)
          assert(timeSheet.workDay == "2014/09/20")
        }

        def testGetSchedulePerCaregiver = {
          val scheduleList = scheduleService.getSchedulePerCaregiver(careID)
          scheduleList foreach {case (s: Schedule) =>
            assert(s.caregiverId == careID)
          }

        }

        def testGetSchedulePerPatient = {
          val scheduleList = scheduleService.getSchedulePerPatient(patID)
          scheduleList foreach {case (s: Schedule) =>
            assert(s.patientId == patID)
          }
        }

        info("Creating Schedule")
        testCreateSchedule
        info("Retrieving TimeSheet Details")
        testGetTimesheetDetails
        info("Retrieving Schedule Detail Per Caregiver")
        testGetSchedulePerCaregiver
        info("Retrieving Schedule Detail Per Patient")
        testGetSchedulePerPatient
      }
    }
  }



}
