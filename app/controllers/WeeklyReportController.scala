package controllers

import java.util.StringTokenizer

import domain.{DailyReport, Referral, WeeklyReport}
import model.{ReferralModel, WeeklyReportModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.WeeklyReportService
import services.impl.WeeklyReportServiceImpl

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tonata on 10/8/14.
 */
object WeeklyReportController extends Controller  {

  val weeklyReportService: WeeklyReportService = new WeeklyReportServiceImpl()

  implicit val weeklyReportWrites = Json.writes[WeeklyReport]
  implicit val referralWrites = Json.writes[Referral]
  implicit val dailyReportWrites = Json.writes[DailyReport]

  def create(js: String) = Action.async(parse.json){
    request =>

      val input = request.body
      //println(input)

      val wReport = (input \ "report").as[String]
      val referral = (input \ "referral").as[String]
      val ids = (input \ "list").as[String]

      val token = new StringTokenizer(ids, "[#]")
      var idList = new ListBuffer[Long]
      token.nextToken()

      while (token.hasMoreTokens){
       val q = token.nextToken()
        //println(q)
        if(q == '\u0022'){
          println('\u0022')
          idList += q.toLong
          println(q.toLong)
        }


      }

      val json = Json.parse(wReport)
      val referralJson = Json.parse(referral)

      val reportObj = Json.fromJson[WeeklyReportModel](json).get
      val reportDom = reportObj.getDomain()

      val referralObj = Json.fromJson[ReferralModel](referralJson).get
      val referralDom = referralObj.getDomain()

      val repObj = WeeklyReport(reportDom.weeklyReportId, reportDom.weekStartDate, reportDom.weekEndDate,
        reportDom.discharges, reportDom.visits, reportDom.monthlyReportID)
      val rObj = Referral(referralDom.referralId,referralDom.referralDate, referralDom.weeklyReportId )

      val results : Future[Long] = Future{weeklyReportService.createWeeklyReport(repObj, rObj, idList.toList)}

      results.map( res =>
        Ok(Json.toJson(res)
        )
      )

  }

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
