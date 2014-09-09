package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class User (
                  userId:Long,
                  username:String,
                  password:String,
                  caregiverId:Option[Long],
                  coordinatorId:Option[Long]
                  )

object Users{
  implicit lazy val userFmt = Json.format[User]
}
