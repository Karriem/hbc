package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Contact (
                     homeTel:Option[String],
                     cellNumber:String,
                     email:String,
                     personId:Option[String],
                     coordinatorId:Option[String],
                     patientId:Option[String],
                     caregiverId:Option[String]
                     )

object Contact{
  implicit lazy val contactFmt = Json.format[Contact]
}

