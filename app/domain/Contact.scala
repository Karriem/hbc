package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Contact (
                     homeTel:Option[String],
                     cellNumber:String,
                     email:String,
                     personId:Option[Long],
                     instituteId:Option[Long],
                     coordinatorId:Option[Long],
                     patientId:Option[Long],
                     caregiverId:Option[Long],
                     unplannedVisitID:Option[Long]
                     )

object Contacts{
  implicit lazy val contactFmt = Json.format[Contact]
}

