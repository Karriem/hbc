package domain

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Diagnosis (
                       diagnosisId:String,
                       diagnosisType:String,
                       treatment:String,
                       followUpDate:DateTime,
                       dailyReportID:String
                       )

object Diagnosis{
  implicit lazy val diagnosisFmt = Json.format[Diagnosis]
}