package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class CarePlan (
                      planId:String,
                      description:String,
                      startDate:DateTime,
                      endDate:DateTime
                      )

object CarePlan{
  implicit lazy val carePlanFmt = Json.format[CarePlan]
}
