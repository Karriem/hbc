package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/08.
 */
case class Measurement (measurementID: Long,
                         dateTaken: Date,
                         weight: Double,
                         bloodPressure: Double,
                         temperature: Double,
                         patientID: Long,
                         caregiverID: Long
                         )

object Measurements{
 implicit lazy val measurementsFmt = Json.format[Measurement]
}
