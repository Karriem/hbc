package model

import domain.Patient
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class PatientModel(patientId:Long,
                   dateOfContact:String,
                   dateOfEvaluation:String,
                   firstName:String,
                   LastName:String){

  def getDomain(): Patient = PatientModel.domain(this)

}

object PatientModel {

  implicit val patientFmt = Json.format[PatientModel]

  def domain(model : PatientModel) = {

    Patient(model.patientId,
      DateTime.parse(model.dateOfContact).toDate,
      DateTime.parse(model.dateOfEvaluation).toDate,
      model.firstName,
      model.LastName)
  }
}
