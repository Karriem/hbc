package services

import domain.{Adherence, CarePlan, Diagnosis, Patient}
import repository.CarePlanModel.CarePlanRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait PatientService {
  def getDiagnosis(id : Long) : Diagnosis
  def getAdherence(id : Long) : Adherence

}
