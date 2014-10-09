package model

import java.util.Date

import domain.Diagnosis
import play.api.libs.json.Json

/**
 * Created by tonata on 10/8/14.
 */
case class DiagnosisModel (diagnosisId: Long,
                           diagnosisType: String,
                           treatment: String,
                           followUpDate: Date,
                           dailyReportId: Option[Long],
                           eventType: String)
{ def getDomain() : Diagnosis = DiagnosisModel.domain(this)}

object DiagnosisModel {
  implicit lazy val diagnosisFmt = Json.format[DiagnosisModel]

  def domain(model: DiagnosisModel) = {
    Diagnosis(model.diagnosisId,
              model.diagnosisType,
              model.treatment,
              model.followUpDate,
              model.dailyReportId,
              model.eventType)
  }
}
