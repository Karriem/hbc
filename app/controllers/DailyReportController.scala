package controllers

import domain.{Diagnosis, Category, TimeSheet, DailyReport}
import model.{CategoryModel, TimeSheetModel, DailyReportModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.DailyReportService
import services.impl.DailyReportServiceImpl

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tonata on 10/8/14.
 */
object DailyReportController extends Controller {

  val dailyReportService: DailyReportService = new DailyReportServiceImpl()

  implicit val dailyReportWrites = Json.writes[DailyReport]
  implicit val timeSheet = Json.writes[TimeSheet]
  implicit val category = Json.writes[Category]
  implicit val diagnosis = Json.writes[Diagnosis]

  def create(js: String) = Action.async(parse.json){
    request =>

      val input = request.body
      println(input)
      //val list = Json.fromJson[List[String]](input).get

      val dReport = (input \ "report").as[String]
      val timeSheet = (input \ "timesheet").as[String]
      val cat = (input \ "category").as[String]
      val diagnosisID = (input \ "diagnosis").as[Long]

      val json = Json.parse(dReport)
      val timesheetJson = Json.parse(timeSheet)
      val categoryJson = Json.parse(cat)

      val reportObj = Json.fromJson[DailyReportModel](json).get
      val reportDom = reportObj.getDomain()

      val timeSheetObj = Json.fromJson[TimeSheetModel](timesheetJson).get
      val timeSheetDom = timeSheetObj.getDomain()

      val catObj = Json.fromJson[CategoryModel](categoryJson).get
      val catDom = catObj.getDomain()

      val rObj = DailyReport(reportDom.dailyReportId, reportDom.servicesRendered, reportDom.weeklyReportId,
        reportDom.caregiverId, reportDom.patientId)
      val tObj = TimeSheet(timeSheetDom.workDay, timeSheetDom.timeIn, timeSheetDom.timeOut,
        timeSheetDom.visitId, timeSheetDom.dailyReportId, timeSheetDom.scheduleId)
      val cObj = Category(catDom.description, catDom.level, catDom.dailyReportId)

      val results : Future[Long] = Future{dailyReportService.createDailyReport(rObj,tObj, cObj,diagnosisID)}

      results.map( res =>
        Ok(Json.toJson(res)

        )
      )
  }

  def getTimeSheetDetails(id: Long) = Action{
    val timesheet = dailyReportService.getTimeSheetDetails(id)
    val json = Json.toJson(timesheet)
    Ok(json)
  }

  def getCategory(id: Long) = Action{
    val cat = dailyReportService.getCategory(id)
    val json = Json.toJson(cat)
    Ok(json)
  }

  def getDiagnosis(id: Long) = Action{
    val diag = dailyReportService.getDiagnosis(id)
    val json = Json.toJson(diag)
    Ok(json)
  }

  def getReportByPatient(id: Long) = Action{
    val reports = dailyReportService.getReportByPatient(id)
    val json = Json.toJson(reports)
    Ok(json)
  }

  def getReportByCaregiver(id: Long) = Action{
    val reports = dailyReportService.getReportByCaregiver(id)
    val json = Json.toJson(reports)
    Ok(json)
  }

  def getAllReports = Action{
    val allReports = dailyReportService.getAllReports()
    val json = Json.toJson(allReports)
    Ok(json)
  }
}
