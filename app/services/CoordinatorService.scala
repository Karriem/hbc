package services

import domain._
import repository.PatientModel.PatientRepo

/**
 * Created by karriem on 9/18/14.
 */
trait CoordinatorService {

  def getInstitution(id:Long): Institution

  def viewPatients(id:Long): Patient

  def viewAllPatient(): List[PatientRepo#TableElementType]

  def createUser(user : User) :Long

  def getUser(id:Long) : User

  def addCoordinator(co : Coordinator):Long

  def createCarePlan(care : CarePlan) :Long

  def addCaregiver(giver : Caregiver) :Long

  def addPatient(pat : Patient) : Long
}
