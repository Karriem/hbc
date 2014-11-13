package model

import domain.Screening
import play.api.libs.json.Json

/**
 * Created by karriem on 11/13/14.
 */
case class ScreeningModel(screeningId:Long,
                          screener:String,
                          illnessType:String) {

  def domain() : Screening = ScreeningModel.getDomain(this)
}

object ScreeningModel {

  implicit lazy val screeningFmt = Json.format[Screening]

  def getDomain(model:ScreeningModel)= {

    Screening(
      model.screeningId,
      model.screener,
      model.illnessType)

  }
}


