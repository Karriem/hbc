package services

import domain.{CarePlan, Coordinator, Patient}

/**
 * Created by karriem on 9/16/14.
 */
trait CarePlanService {

  def createPlan(care:CarePlan):Long

  def updateCarePlan(care:CarePlan, id:Long)

  def getPatient(id:Long) : Patient

  def getPlanIssued(id:Long) : Coordinator

  def getVisit(id:Long) : CarePlan
}
