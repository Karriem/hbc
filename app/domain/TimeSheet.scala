package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class TimeSheet (
                       workDay:Date,
                       timeIn:Date,
                       timeOut:Date,
                       visitId:Option[Long],
                       dailyReportId:Option[Long],
                       scheduleId:Option[Long]
                       )

object TimeSheets{
  implicit lazy val timeSheetFmt = Json.format[TimeSheet]
}


