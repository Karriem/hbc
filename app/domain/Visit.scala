package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Visit (
                   visitId:Long,
                   nextVisit:String,
                   carePlanId:Long
                   )

object Visits{
  implicit lazy val visitFmt = Json.format[Visit]
}
