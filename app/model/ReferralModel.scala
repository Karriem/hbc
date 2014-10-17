package model

import domain.Referral
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/17.
 */
case class ReferralModel(referralId:Long,
                         referralDate:String,
                         weeklyReportId:String){
  def getDomain() : Referral = ReferralModel.domain(this) }

object ReferralModel{
  implicit lazy val referralFmt = Json.format[ReferralModel]

  def domain(model: ReferralModel)={

    var value : Long = 0

    if (model.weeklyReportId == ""){
      value
    }
    else
    if(model.weeklyReportId != ""){
      value = model.weeklyReportId.toLong
    }


    Referral(model.referralId,
             DateTime.parse(model.referralDate).toDate,
             Some(value))
  }
}
