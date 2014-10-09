package model

import domain.User
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class UserModel(userId:Long,
                     username:String,
                     password:String,
                     caregiverId:Option[Long],
                     coordinatorId:Option[Long]) {

  def getDomain(): User = UserModel.domain(this)
}

object UserModel {

  implicit val userFmt = Json.format[UserModel]

  def domain(model : UserModel) = {

    User(model.userId,
      model.username,
      model.password,
      model.caregiverId,
      model.coordinatorId)
  }
}
