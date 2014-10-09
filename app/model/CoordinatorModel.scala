package model

import domain.Coordinator
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class CoordinatorModel(coId:String,
                            firstName:String,
                            lastName:String) {

 def getDomain(): Coordinator = CoordinatorModel.domain(this)
}

object CoordinatorModel {

  implicit val coordinatorFmt = Json.format[CoordinatorModel]

  def domain(model : CoordinatorModel) = {

    Coordinator(model.coId.toLong,
      model.firstName,
      model.lastName)
  }
}
