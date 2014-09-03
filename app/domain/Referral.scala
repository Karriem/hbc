package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Referral (
                      referralId:String,
                      referralDate:DateTime,
                      monthlyReportId:Option[String]
                      )

object Referral{
  implicit lazy val referralFmt = Json.format[Referral]
}
