package domain

import java.sql.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Visit (
                   visitId:Long,
                   nextVisit:Date,
                   carePlanId:Long
                   )

object Visits{
  implicit lazy val visitFmt = Json.format[Visit]
}
