package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 9/2/14.
 */
case class Medication(
                       mType:String,
                       instructions:String,
                       patientId:String
                       )

object Medication{
  implicit lazy val medicationFmt = Json.format[Medication]
}
