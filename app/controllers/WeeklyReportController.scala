package controllers

import domain.{DailyReport, Referral, WeeklyReport}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.WeeklyReportService
import services.impl.WeeklyReportServiceImpl

/**
 * Created by tonata on 10/8/14.
 */
object WeeklyReportController extends Controller  {

  val weeklyReportService: WeeklyReportService = new WeeklyReportServiceImpl()

  implicit val weeklyReportWrites = Json.writes[WeeklyReport]
  implicit val referralWrites = Json.writes[Referral]
  implicit val dailyReportWrites = Json.writes[DailyReport]

  /*def create() = Action{

  }*/

  def getTotalVisits(id: Long) = Action {
    val visits = weeklyReportService.getTotalVisits(id)
    val json = Json.toJson(visits)
    Ok(json)
  }

  def checkForReferral(id: Long) = Action {
    val referral = weeklyReportService.checkForReferral(id)
    val json = Json.toJson(referral)
    Ok(json)
  }

  def getAllDailyReports(id: Long) = Action {
    val allDailyReports = weeklyReportService.getAllDailyReports(id)
    val json = Json.toJson(allDailyReports)
    Ok(json)
  }

}
