package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class DailyReport (
                         dailyReportId:String,
                         servicesRendered:String,
                         monthlyReportId:String
                         )

object DailyReport{
  implicit lazy val dailyReportFmt = Json.format[DailyReport]
}
