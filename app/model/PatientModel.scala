package model

import java.util.Date

import domain.Patient
import play.api.libs.json.Json

/**
 * Created by karriem on 10/8/14.
 */
case class PatientModel(patientId:Long,
                   dateOfContact:Date,
                   dateOfEvaluation:Date,
                   firstName:String,
                   LastName:String){

  def getDomain(): Patient = PatientModel.domain(this)

}

object PatientModel {

  implicit val patientFmt = Json.format[PatientModel]

  def domain(model : PatientModel) = {

    Patient(model.patientId,
      model.dateOfContact,
      model.dateOfEvaluation,
      model.firstName,
      model.LastName)
  }

}
