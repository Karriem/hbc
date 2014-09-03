package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class User (
                  userId:String,
                  username:String,
                  password:String,
                  caregiverId:Option[String],
                  coordinatorId:Option[String]
                  )

object User{
  implicit lazy val userFmt = Json.format[User]
}
