package services

import domain.CarePlan
import repository.CarePlanModel.CarePlanRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo

/**
 * Created by karriem on 9/16/14.
 */
trait CarePlanService {

  def createPlan(care:CarePlan)

  def getPatient(id:Long) : List[PatientRepo#TableElementType]

  def getPlanIssued(id:Long) : List[CoordinatorRepo#TableElementType]

  def getVisit(id:Long) : List[CarePlanRepo#TableElementType]
}
