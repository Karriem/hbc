package services

import domain.{Visit, Coordinator, Patient, CarePlan}

/**
 * Created by karriem on 9/16/14.
 */
trait CarePlanService {

  def createPlan(care:CarePlan)

  def getPatient(id:Long) : Patient

  def getPlanIssued(id:Long) : Coordinator

  def getVisit(id:Long) : Visit
}
