package model

import domain.ClientAssessment
import play.api.libs.json.Json

/**
 * Created by karriem on 11/13/14.
 */
case class ClientAssessmentModel (activity:String,
                                  category:Int,
                                  referralId:Long){

  def domain() : ClientAssessment = ClientAssessmentModel.getDomain(this)
}

object ClientAssessmentModel {

  implicit lazy val clientAssFmt = Json.format[ClientAssessment]

  def getDomain(model:ClientAssessmentModel) ={

    ClientAssessment(model.activity,
                     model.category,
                     model.referralId)
  }
}

