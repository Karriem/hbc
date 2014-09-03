package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Visit (
                   visitId:String,
                   nextVisit:DateTime,
                   carePlanId:String
                   )

object Visit{
  implicit lazy val visitFmt = Json.format[Visit]
}
