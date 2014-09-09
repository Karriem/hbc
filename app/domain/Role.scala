package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Role (
                  roleId:Long,
                  description:String,
                  userId:Option[Long]
                  )

object Roles{
  implicit lazy val roleFmt = Json.format[Role]
}
