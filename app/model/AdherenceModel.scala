package model

import domain.Adherence
import play.api.libs.json.Json

/**
 * Created by karriem on 11/13/14.
 */
case class AdherenceModel(adType:String,
                          instructions:String,
                          patientId:Long,
                          referralId:Long) {

  def domain() : Adherence = AdherenceModel.getDomain(this)
}

object AdherenceModel {
  implicit lazy val adherenceFmt = Json.format[Adherence]

  def getDomain(model: AdherenceModel) ={

    Adherence(model.adType,
              model.instructions,
              model.patientId,
              model.referralId)
  }
}