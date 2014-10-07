package domain

import java.util.Date


import play.api.libs.json.Json


/**
 * Created by karriem on 8/28/14.
 */
 case class Diagnosis(
                      diagnosisId: Long,
                      diagnosisType: String,
                      treatment: String,
                      followUpDate: Date,
                      dailyReportId: Option[Long],
                      eventType: String
                      ) {
  //def a() = { Diagnosises.diagnosisFmt}
}

object Diagnosises{
  implicit lazy val diagnosisFmt = Json.format[Diagnosis]
}