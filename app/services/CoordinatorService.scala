package services

import domain.{Coordinator, User}

/**
 * Created by karriem on 9/18/14.
 */
trait CoordinatorService {

  def getInstitution(id:Long)

  def viewPatients(id:Long)

  def viewAllPatient()

  def createUser(user : User)

  def getUser(id:Long)

  def addCoordinator(co : Coordinator)
}
