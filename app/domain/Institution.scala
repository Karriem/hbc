package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Institution (
                         instituteId:Long,
                         instituteType:String,
                         instituteName:String,
                         coordinatorId:Option[Long],
                         referralId:Long
                         )

object Institutions{
  implicit lazy val institutionFmt = Json.format[Institution]
}