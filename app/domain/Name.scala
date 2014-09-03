package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Name (
                  firstName:String,
                  lastName:String,
                  coordinatorId:Option[String],
                  personID:Option[String],
                  patientId:Option[String],
                  caregiverId:Option[String]
                  )

object Name{
  implicit  lazy  val nameFmt = Json.format[Name]
}
