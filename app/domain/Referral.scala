package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Referral (
                      referralId:Long,
                      referralDate:Date,
                      weeklyReportId:Option[Long]
                      )

object Referrals{
  implicit lazy val referralFmt = Json.format[Referral]
}
