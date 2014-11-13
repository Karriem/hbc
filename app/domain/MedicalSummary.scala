package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/08.
 */
case class MedicalSummary (medicalSummaryID: Long,
                           dateTaken: Date,
                           weight: Double,
                           bloodPressure: Double,
                           temperature: Double,
                           patientID: Long,
                           caregiverID: Long,
                           allergy:String,
                           finalDiagnosis:String,
                           reportsAttached:Boolean,
                           referToCHC:String
                         )

object MedicalSummaries{
 implicit lazy val measurementsFmt = Json.format[MedicalSummary]
}
