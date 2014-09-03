package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class TimeSheet (
                       workDay:DateTime,
                       timeIn:DateTime,
                       timeOut:DateTime,
                       visitId:String,
                       dailyReportId:Option[String],
                       scheduleId:Option[String]
                       )

object TimeSheet{
  implicit lazy val timeSheetFmt = Json.format[TimeSheet]
}


