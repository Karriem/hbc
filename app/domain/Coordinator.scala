package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Coordinator (
                         coId:String,
                         carePlanId:String
                         )

object Coordinator{
  implicit lazy val coordinatorFmt = Json.format[Coordinator]
}


