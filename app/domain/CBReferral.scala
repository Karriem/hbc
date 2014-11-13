package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 11/12/14.
 */
case class CBReferral (
                        cbReferralId:Long,
                        place:String,
                        patientId:Long,
                        dateTaken:Date,
                        healthCondition:String,
                        screeningId:Long,
                        reading:String,
                        action:String
                        )


object cbReferrals {
  implicit lazy val cbFmt = Json.format[CBReferral]
}