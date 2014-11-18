package services

import domain.{TimeSheet, Patient, Schedule, Caregiver}
import org.joda.time.DateTime
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

      val scheduleRepo = TableQuery[ScheduleRepo]
      val caregiverRepo = TableQuery[CaregiverRepo]
      val patientRepo = TableQuery[PatientRepo]
      val timesheetRepo = TableQuery[TimeSheetRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val schedule = Schedule(1L, 1L, 1L)

        val wd = new DateTime(2014 , 9, 20, 0, 0)
        val ti = new DateTime(2014 , 9, 20, 8, 30)
        val to = new DateTime(2014 , 9, 20, 12, 30)


        val caregiver = Caregiver(1L, "Tonata", "Nakashololo")
        val caregiverID = caregiverRepo.returning(caregiverRepo.map (_.caregiverId)).insert(caregiver)

        val patient = Patient(1L, DateTime.parse("2013-12-2").toDate, DateTime.parse("2014-04-12").toDate, "Helvi", "Kalenga", "Stuff", "24548844", "Stuff", "Japanese", "Things")
        val patientID = patientRepo.returning(patientRepo.map (_.patientId)).insert(patient)

        val timesheet = TimeSheet(wd.toDate, ti.toDate, to.toDate, None, None, Option(1L))

        val scheduleService : ScheduleService = new ScheduleServiceImpl()


        var id: Long = 0L
        var careID: Long = 0L
        var patID: Long = 0L

        def testCreateSchedule = {
          id = scheduleService.createSchedule(schedule, caregiverID, patientID, timesheet)
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
          assert(timeSheet.workDay == wd.toDate)
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
