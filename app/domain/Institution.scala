package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Institution (
                         instituteId:String,
                         instituteType:String,
                         instituteName:String,
                         coordinatorId:Option[String],
                         referralId:String
                         )

object Institution{
  implicit lazy val institutionFmt = Json.format[Institution]
}