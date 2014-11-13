package model

import java.util.Date

import domain.CBReferral
import play.api.libs.json.Json

/**
 * Created by karriem on 11/13/14.
 */
case class CBReferralModel (cbReferralId:Long,
                            place:String,
                            patientId:Long,
                            dateTaken:Date,
                            healthCondition:String,
                            screeningId:Long,
                            reading:String,
                            action:String){

  def domain() : CBReferral = CBReferralModel.getDomain(this)
}

object CBReferralModel{

  implicit lazy val cbreferralFmt = Json.format[CBReferral]

  def getDomain(model:CBReferralModel)={

    CBReferral(model.cbReferralId,
               model.place,
               model.patientId,
               model.dateTaken,
               model.healthCondition,
               model.screeningId,
               model.reading,
               model.action)
  }
}

