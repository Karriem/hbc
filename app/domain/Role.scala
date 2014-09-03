package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Role (
                  roleId:String,
                  description:String,
                  userId:Option[String]
                  )

object Role{
  implicit lazy val roleFmt = Json.format[Role]
}
