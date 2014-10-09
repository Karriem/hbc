package model

import domain.User
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class UserModel(userId:String,
                     username:String,
                     password:String,
                     caregiverId:String,
                     coordinatorId:String) {

  def getDomain(): User = UserModel.domain(this)
}

object UserModel {

  implicit val userFmt = Json.format[UserModel]

  def domain(model : UserModel) = {

    var value : Long = 0
    var value2 : Long = 0

    if (model.caregiverId == ""){
      value
    }
    else
    if(model.caregiverId != ""){
      value = model.caregiverId.toLong
    }

    if (model.coordinatorId == ""){
      value2
    }
    else
    if(model.coordinatorId != ""){
      value2 = model.coordinatorId.toLong
    }

    User(model.userId.toLong,
      model.username,
      model.password,
      Some(value),
      Some(value2))
  }
}
