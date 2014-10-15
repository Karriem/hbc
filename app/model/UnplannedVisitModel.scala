package model

import domain.UnplannedVisit
import org.joda.time.DateTime
import play.api.libs.json.Json


/**
 * Created by tonata on 10/10/14.
 */
case class UnplannedVisitModel (unplannedVisitID:Long,
                                visitDate: String,
                                patientName: String,
                                patientLastName: String,
                                caregiverID: String){
  def getDomain(): UnplannedVisit = UnplannedVisitModel.domain(this)
}

object UnplannedVisitModel{

  implicit val caregiverFmt = Json.format[UnplannedVisitModel]

  def domain(model : UnplannedVisitModel) = {
    UnplannedVisit(model.unplannedVisitID,
                  DateTime.parse(model.visitDate).toDate,
                  model.patientName,
                  model.patientLastName,
                  (model.caregiverID).toLong)
  }

}
