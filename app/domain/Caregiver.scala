package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Caregiver (
                       caregiverId:String,
                       scheduleId:String,
                       dailyReportId:String
                       )

object Caregiver{
  implicit lazy val caregiverFmt = Json.format[Caregiver]
}
