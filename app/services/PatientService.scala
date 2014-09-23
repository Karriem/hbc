package services

import domain.{Diagnosis, Patient}
import repository.CarePlanModel.CarePlanRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait PatientService {
  def addPatient(patient : Patient)
  def getDiagnosis(id : Long) : List[DiagnosisRepo#TableElementType]
  def getCarePlans(id: Long) //: List[PatientRepo#TableElementType]
  def displayCarePlan(id : Long) : List[CarePlanRepo#TableElementType]

}
