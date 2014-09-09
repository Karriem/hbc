package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Diagnosis (
                       diagnosisId:Long,
                       diagnosisType:String,
                       treatment:String,
                       followUpDate:String,
                       dailyReportId:Long
                       )

object Diagnosises{
  implicit lazy val diagnosisFmt = Json.format[Diagnosis]
}