package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class DailyReport (
                           dailyReportId:Long,
                           servicesRendered:String,
                           weeklyReportId:Option[Long],
                           caregiverId:Long,
                           patientId:Long
                         )

object DailyReports{
  implicit lazy val dailyReportFmt = Json.format[DailyReport]
}
