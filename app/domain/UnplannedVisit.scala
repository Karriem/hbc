package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by tonata on 10/7/14.
 */
case class UnplannedVisit (unplannedVisitID:Long,
                           visitDate: Date,
                           patientName: String,
                           patientLastName: String,
                           caregiverID: Long)

object UnplannedVisits{
  implicit lazy val unplannedVisitFmt = Json.format[UnplannedVisit]
}
