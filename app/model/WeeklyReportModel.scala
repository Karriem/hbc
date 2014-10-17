package model

import domain.WeeklyReport
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by tonata on 10/10/14.
 */
case class WeeklyReportModel (weeklyReportId:Long,
                              weekStartDate: String,
                              weekEndDate: String,
                              discharges: String,
                              visits:String,
                              monthlyReportID: String){
  def getDomain() : WeeklyReport = WeeklyReportModel.domain(this) }


object WeeklyReportModel{
  implicit lazy val weeklyReportFmt = Json.format[WeeklyReportModel]

  def domain(model: WeeklyReportModel )={

    var value2 : Long = 0
    var w : WeeklyReport = null
    if (model.monthlyReportID == ""){

      w = WeeklyReport(model.weeklyReportId,
        DateTime.parse(model.weekStartDate).toDate,
        DateTime.parse(model.weekEndDate).toDate,
        model.discharges,
        (model.visits).toInt,
        None)

    }
    else
    if(model.monthlyReportID != ""){
      value2 = model.monthlyReportID.toLong

      w = WeeklyReport(model.weeklyReportId,
        DateTime.parse(model.weekStartDate).toDate,
        DateTime.parse(model.weekEndDate).toDate,
        model.discharges,
        (model.visits).toInt,
        Some(value2))
    }

    w

  }
}
