package controllers

import domain.{TimeSheet, Schedule}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ScheduleService
import services.impl.ScheduleServiceImpl

/**
 * Created by tonata on 10/8/14.
 */
object ScheduleController  extends Controller{

  val scheduleService: ScheduleService = new ScheduleServiceImpl()

  implicit val scheduleWrites = Json.writes[Schedule]
  implicit val timeSheet = Json.writes[TimeSheet]

  /*def createSchedule() = Action{
    scheduleService.createSchedule()
  }*/

 def getTimeSheetDetails(id: Long) = Action{
    val timesheet = scheduleService.getTimesheetDetails(id)
    val json = Json.toJson(timesheet)
    Ok(json)
  }

  def getSchedulePerCaregiver(id: Long) = Action{
    val Schedules = scheduleService.getSchedulePerCaregiver(id)
    val json = Json.toJson(Schedules)
    Ok(json)
  }

  def getSchedulePerPatient(id: Long) = Action{
    val pSchedules = scheduleService.getSchedulePerPatient(id)
    val json = Json.toJson(pSchedules)
    Ok(json)
  }
}
