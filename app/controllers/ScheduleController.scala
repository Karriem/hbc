package controllers

import domain.{TimeSheet, Schedule}
import model._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.ScheduleService
import services.impl.ScheduleServiceImpl

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tonata on 10/8/14.
 */
object ScheduleController  extends Controller{

  val scheduleService: ScheduleService = new ScheduleServiceImpl()

  implicit val scheduleWrites = Json.writes[Schedule]
  implicit val timeSheet = Json.writes[TimeSheet]

  def createSchedule(schedule: String) = Action.async(parse.json){
    request =>
      val input = request.body
      val schModel = Json.fromJson[ScheduleModel](input).get
      val schduleObj = schModel.getDomain()

      val caregiverModel = Json.fromJson[Long](input).get
      val caregivObj = caregiverModel.toLong

      val patientModel = Json.fromJson[Long](input).get
      val patientObj = patientModel.toLong

      val timeModel = Json.fromJson[TimeSheetModel](input).get
      val timeObj = timeModel.getDomain()

      val sch : Future[Long] = Future{scheduleService.createSchedule(schduleObj, caregivObj, patientObj, timeObj)}

      sch.map(use =>
      Ok(Json.toJson(use))
      )
  }

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
