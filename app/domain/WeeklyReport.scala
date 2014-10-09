package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by tonata on 10/7/14.
 */
case class WeeklyReport (weeklyReportId:Long,
                         weekStartDate: Date,
                         weekEndDate: Date,
                         discharges: String,
                         visits:Int,
                         monthlyReportID: Option[Long])


object WeeklyReports{
  implicit lazy val weeklyReportFmt = Json.format[WeeklyReport]
}
