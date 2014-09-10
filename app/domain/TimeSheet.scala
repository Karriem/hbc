package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class TimeSheet (
                       workDay:String,
                       timeIn:String,
                       timeOut:String,
                       visitId:Option[Long],
                       dailyReportId:Option[Long],
                       scheduleId:Option[Long]
                       )

object TimeSheets{
  implicit lazy val timeSheetFmt = Json.format[TimeSheet]
}


