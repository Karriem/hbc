package services

import domain.{CarePlan, Diagnosis, Patient}
import repository.CarePlanModel.CarePlanRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait PatientService {
  def addPatient(patient : Patient) : Long
 // def getPatient(id : Long) : List[PatientRepo#TableElementType]
  def getDiagnosis(id : Long) : Diagnosis
  def displayCarePlan(id : Long) : CarePlan

}
