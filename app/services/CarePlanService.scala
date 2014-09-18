package services

import domain.CarePlan

/**
 * Created by karriem on 9/16/14.
 */
trait CarePlanService {

  def createPlan(care:CarePlan)

  def getPatient(id:Long)

  def getPlanIssued(id:Long)

  def getVisit(id:Long)
}
