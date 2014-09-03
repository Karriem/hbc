package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Patient (
                     patientID:String,
                     dateOfContact:DateTime,
                     dateOfEvaluation:DateTime
                     )

object Patient{
  implicit lazy val patientFmt = Json.format[Patient]
}
