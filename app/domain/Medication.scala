package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 9/2/14.
 */
case class Medication(
                       mType:String,
                       instructions:String,
                       patientId:Long
                       )

object Medications{
  implicit lazy val medicationFmt = Json.format[Medication]
}
