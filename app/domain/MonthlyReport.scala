package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class MonthlyReport (
                           monthlyReportId:Long,
                           visits:Int)

object MonthlyReports{
  implicit lazy val monthlyReportFmt = Json.format[MonthlyReport]
}
