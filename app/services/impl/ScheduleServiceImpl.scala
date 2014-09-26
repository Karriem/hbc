package services.impl

import domain.{Caregiver, Patient, Schedule, TimeSheet}
import repository.CaregiverModel.CaregiverRepo
import repository.PatientModel.PatientRepo
import repository.ScheduleModel.ScheduleRepo
import repository.TimeSheetModel.TimeSheetRepo
import services.ScheduleService

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 9/18/14.
 */
class ScheduleServiceImpl  extends ScheduleService{

  val scheduleRepo = TableQuery[ScheduleRepo]
  val caregiverRepo = TableQuery[CaregiverRepo]
  val patientRepo = TableQuery[PatientRepo]
  val timesheetRepo = TableQuery[TimeSheetRepo]

  override def createSchedule(schedule: Schedule,
                              caregiver: Caregiver,
                              patient: Patient ,
                              timeSheet: TimeSheet): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


      val caregiverID = caregiverRepo.returning(caregiverRepo.map (_.caregiverId)).insert(caregiver)
      val patientID = patientRepo.returning(patientRepo.map (_.patientId)).insert(patient)

      val upSchedule = Schedule(schedule.scheduleId , patientID, caregiverID)
      val schID =  scheduleRepo.returning(scheduleRepo.map (_.scheduleId)).insert(upSchedule)

      val upSheet = TimeSheet(timeSheet.workDay, timeSheet.timeIn, timeSheet.timeOut, timeSheet.visitId, timeSheet.dailyReportId, Option(schID))
      timesheetRepo.insert(upSheet)

      return schID
    }

  }

  override def getTimesheetDetails(id: Long) : TimeSheet= {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return timesheetRepo.filter(_.scheduleId === id).list.head
    }
  }

  override def getSchedulePerCaregiver(id: Long) : List[ScheduleRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return scheduleRepo.filter(_.caregiverId === id).list
    }

  }

  override def getSchedulePerPatient(id: Long): List[ScheduleRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return scheduleRepo.filter(_.patientId === id).list
    }

  }

}
