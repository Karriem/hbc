package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Patient (
                       patientId:Long,
                       dateOfContact:String,
                       dateOfEvaluation:String,
                       firstName:String,
                       LastName:String
                     )

object Patients{
  implicit lazy val patientFmt = Json.format[Patient]
}
