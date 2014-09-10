package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Caregiver (
                          caregiverId:Long,
                          firstName:String,
                          LastName:String
                       )

object Caregivers{
  implicit lazy val caregiverFmt = Json.format[Caregiver]
}
