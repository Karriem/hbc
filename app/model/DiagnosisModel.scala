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
                           dailyReportId: String,
                           eventType: String)
{ def getDomain() : Diagnosis = DiagnosisModel.domain(this)}

object DiagnosisModel {
  implicit lazy val diagnosisFmt = Json.format[DiagnosisModel]

  def domain(model: DiagnosisModel) = {

    var value : Long = 0
    var d : Diagnosis = null

    if (model.dailyReportId == ""){
      d = Diagnosis((model.diagnosisId).toLong,
        model.diagnosisType,
        model.treatment,
        DateTime.parse(model.followUpDate).toDate,
        None,
        model.eventType)
    }
    else
    if(model.dailyReportId != ""){
      d = Diagnosis((model.diagnosisId).toLong,
        model.diagnosisType,
        model.treatment,
        DateTime.parse(model.followUpDate).toDate,
        Some(model.dailyReportId.toLong),
        model.eventType)
    }

    d


  }
}
