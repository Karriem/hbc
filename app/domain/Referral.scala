package domain

import java.sql.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Referral (
                      referralId:Long,
                      referralDate:Date,
                      monthlyReportId:Option[Long]
                      )

object Referrals{
  implicit lazy val referralFmt = Json.format[Referral]
}
