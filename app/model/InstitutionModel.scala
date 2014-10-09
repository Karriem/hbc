package model

import domain.Institution
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class InstitutionModel(instituteId:Long,
                            instituteType:String,
                            instituteName:String,
                            coordinatorId:Option[Long],
                            referralId:Long) {

 def getDomain(): Institution = InstitutionModel.domain(this)
}

object InstitutionModel {

  implicit val InstitutionFmt = Json.format[InstitutionModel]

  def domain(model : InstitutionModel) = {

    Institution(model.instituteId,
      model.instituteType,
      model.instituteName,
      model.coordinatorId,
      model.referralId)
  }
}
