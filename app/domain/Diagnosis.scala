package domain

import java.sql.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Diagnosis (
                       diagnosisId:Long,
                       diagnosisType:String,
                       treatment:String,
                       followUpDate:Date,
                       dailyReportId:Long
                       )

object Diagnosises{
  implicit lazy val diagnosisFmt = Json.format[Diagnosis]
}