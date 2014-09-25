package services

import domain.{TimeSheet, Patient, Caregiver, Schedule}
import repository.ScheduleModel.ScheduleRepo

/**
 * Created by tonata on 9/18/14.
 */
trait ScheduleService {

  def createSchedule(schedule: Schedule,
                     caregiver: Caregiver,
                     patient: Patient ,
                     timeSheet: TimeSheet) : Long

  def getTimesheetDetails(id: Long) : TimeSheet

  def getSchedulePerCaregiver(id: Long): List[ScheduleRepo#TableElementType]

  def getSchedulePerPatient(id: Long): List[ScheduleRepo#TableElementType]



}
