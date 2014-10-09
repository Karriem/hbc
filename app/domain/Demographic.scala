package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Demographic (
                         age:Int,
                         gender:String,
                         dateOfBirth:Date,
                         coordinatorId:Option[Long],
                         personId:Option[Long],
                         patientId:Option[Long],
                         caregiverId:Option[Long]
                         )

object Demographics{
  implicit lazy val demographicFmt = Json.format[Demographic]
}
