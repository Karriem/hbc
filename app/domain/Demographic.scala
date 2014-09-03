package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Demographic (
                         age:Int,
                         gender:String,
                         dateOfBirth:DateTime,
                         coordinatorId:Option[String],
                         personId:Option[String],
                         patientId:Option[String],
                         caregiverId:Option[String]
                         )

object Demographic{
  implicit lazy val demographicFmt = Json.format[Demographic]
}
