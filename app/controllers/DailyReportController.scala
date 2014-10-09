package controllers

import domain.{Diagnosis, Category, TimeSheet, DailyReport}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.DailyReportService
import services.impl.DailyReportServiceImpl

/**
 * Created by tonata on 10/8/14.
 */
object DailyReportController extends Controller {

  val dailyReportService: DailyReportService = new DailyReportServiceImpl()

  implicit val dailyReportWrites = Json.writes[DailyReport]
  implicit val timeSheet = Json.writes[TimeSheet]
  implicit val category = Json.writes[Category]
  implicit val diagnosis = Json.writes[Diagnosis]
  /*def create() = Action{
  }*/

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
