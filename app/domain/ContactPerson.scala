package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class ContactPerson (
                            personId:Long,
                            firstName:String,
                            LastName:String,
                            instituteId: Long
                          )

object ContactPersons{
  implicit lazy val contactPersonFmt = Json.format[ContactPerson]
}
