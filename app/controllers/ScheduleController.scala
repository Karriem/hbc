package controllers

import domain.{Patient, Caregiver, TimeSheet, Schedule}
import model._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import repository.PatientModel.PatientRepo
import services.ScheduleService
import services.impl.ScheduleServiceImpl

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 10/8/14.
 */
object ScheduleController  extends Controller{

  val scheduleService: ScheduleService = new ScheduleServiceImpl()

  implicit val scheduleWrites = Json.writes[Schedule]
  implicit val timeSheet = Json.writes[TimeSheet]
  val repo = TableQuery[PatientRepo]

  def createSchedule(schedule: String) = Action.async(parse.json){
    request =>
      val input = request.body

      val sch = (input \ "schedule").as[String]
      val time = (input \ "timesheet").as[String]
      val patID = (input \ "patid").as[Long]
      val careID = (input \ "careid").as[Long]

      val jsonSch = Json.parse(sch)
      val timeJson = Json.parse(time)

      val schModel = Json.fromJson[ScheduleModel](jsonSch).get
      val scheduleObj = schModel.getDomain()

      val timeModel = Json.fromJson[TimeSheetModel](timeJson).get
      val timeObj = timeModel.getDomain()

      val sObj = Schedule(scheduleObj.scheduleId , scheduleObj.patientId, scheduleObj.caregiverId)

      val tObj = TimeSheet(timeObj.workDay, timeObj.timeIn, timeObj.timeOut , timeObj.visitId, timeObj.dailyReportId, timeObj.scheduleId)

      val results : Future[Long] = Future{scheduleService.createSchedule(sObj, careID, patID,  tObj)}

      results.map(use =>
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
