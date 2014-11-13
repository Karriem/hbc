package model

import domain.MedicalSummary
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by tonata on 10/8/14.
 */
case class MeasurementModel (measurementID: String,
                             dateTaken: String,
                             weight: String,
                             bloodPressure: String,
                             temperature: String,
                             patientID: String,
                             caregiverID: String,
                             allergy:String,
                             finalDiagnosis:String,
                             reportsAttached:Boolean,
                             referToCHC:String)
{ def getDomain() : MedicalSummary = MeasurementModel.domain(this)}

object MeasurementModel {
  implicit lazy val measurementsFmt = Json.format[MeasurementModel]

  def domain (model: MeasurementModel ) ={
    MedicalSummary(model.measurementID.toLong,
                DateTime.parse(model.dateTaken).toDate,
                model.weight.toDouble,
                model.bloodPressure.toDouble,
                model.temperature.toDouble,
                model.patientID.toLong,
                model.caregiverID.toLong,
                model.allergy,
                model.finalDiagnosis,
                model.reportsAttached,
                model.referToCHC)
  }
}
