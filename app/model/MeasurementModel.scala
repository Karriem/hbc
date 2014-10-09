package model

import java.util.Date

import domain.Measurement
import play.api.libs.json.Json

/**
 * Created by tonata on 10/8/14.
 */
case class MeasurementModel (measurementID: Long,
                             dateTaken: Date,
                             weight: Double,
                             bloodPressure: Double,
                             temperature: Double,
                             patientID: Long,
                             caregiverID: Long)
{ def getDomain() : Measurement = MeasurementModel.domain(this)}

object MeasurementModel {
  implicit lazy val measurementsFmt = Json.format[MeasurementModel]

  def domain (model: MeasurementModel ) ={
    Measurement(model.measurementID,
                model.dateTaken,
                model.weight,
                model.bloodPressure,
                model.temperature,
                model.patientID,
                model.caregiverID)
  }
}
