package model

import java.util.Date

import domain.Diagnosis
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by tonata on 10/8/14.
 */
case class DiagnosisModel (diagnosisId: String,
                           diagnosisType: String,
                           treatment: String,
                           followUpDate: String,
                           dailyReportId: Long,
                           eventType: String)
{ def getDomain() : Diagnosis = DiagnosisModel.domain(this)}

object DiagnosisModel {
  implicit lazy val diagnosisFmt = Json.format[DiagnosisModel]

  def domain(model: DiagnosisModel) = {
    Diagnosis((model.diagnosisId).toLong,
              model.diagnosisType,
              model.treatment,
              DateTime.parse(model.followUpDate).toDate,
              Option(model.dailyReportId),
              model.eventType)
  }
}
