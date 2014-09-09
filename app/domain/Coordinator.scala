package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Coordinator (
                          coId:Long,
                          firstName:String,
                          lastName:String
                         )

object Coordinators{
  implicit lazy val coordinatorFmt = Json.format[Coordinator]
}


