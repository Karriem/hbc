package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 9/2/14.
 */
case class Adherence(
                       adType:String,
                       instructions:String,
                       patientId:Long,
                       referralId:Long
                       )

object Adherences{
  implicit lazy val adherenceFmt = Json.format[Adherence]
}
