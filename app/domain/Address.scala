package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Address (
                     streetAddress:String,
                     postalAddress:String,
                     postalCode:String,
                     personId:Option[String],
                     instituteId:Option[String],
                     patientId:Option[String],
                     caregiverId:Option[String],
                     coordinatorId:Option[String]
                     )

object Address{
  implicit lazy val addressFmt = Json.format[Address]
}