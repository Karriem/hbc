package model

import domain.Caregiver
import play.api.libs.json.Json

/**
 * Created by karriem on 10/9/14.
 */
case class CaregiverModel(caregiverId:Long,
                          firstName:String,
                          LastName:String) {

  def getDomain(): Caregiver = CaregiverModel.domain(this)
}

object CaregiverModel {

  implicit val caregiverFmt = Json.format[CaregiverModel]

  def domain(model : CaregiverModel) = {

    Caregiver(model.caregiverId,
      model.firstName,
      model.LastName)
  }

}
