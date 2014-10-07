package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Address (
                     streetAddress:String,
                     postalAddress:String,
                     postalCode:String,
                     personId:Option[Long],
                     instituteId:Option[Long],
                     patientId:Option[Long],
                     caregiverId:Option[Long],
                     coordinatorId:Option[Long],
                     unplannedVisitID:Option[Long]
                     )

object Addresses{
  implicit lazy val addressFmt = Json.format[Address]
}