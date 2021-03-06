package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Patient (
                       patientId:Long,
                       dateOfContact:Date,
                       dateOfEvaluation:Date,
                       firstName:String,
                       lastName:String,
                       nextOfKin:String,
                       nextOfKinTel:String,
                       religion:String,
                       language:String,
                       carePlanDesc:String
                     )

object Patients{
  implicit lazy val patientFmt = Json.format[Patient]
}
