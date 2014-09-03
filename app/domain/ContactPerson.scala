package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class ContactPerson (
                           personId:String
                           )

object ContactPerson{
  implicit lazy val contactPersonFmt = Json.format[ContactPerson]
}
