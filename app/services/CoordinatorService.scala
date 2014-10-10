package services

import domain._
import repository.PatientModel.PatientRepo

/**
 * Created by karriem on 9/18/14.
 */
trait CoordinatorService {

  def addCoordinator(co : Coordinator):Long

  def createUser(user : User) :Long

  def addCaregiver(giver : Caregiver) :Long

  def addPatient(pat : Patient) : Long

  def createCarePlan(care : CarePlan) :Long
//----------------------------------------------------------
  def updateCoordinator(co:Coordinator, id:Long) :Long

  def updateUser(user:User, id:Long) : Long

  def updateCaregiver(giver:Caregiver, id:Long) :Long

  def updatePatient(pat:Patient, id:Long) :Long

  def updateCarePlan(care:CarePlan, id:Long) :Long

  def deleteCoordinator(id:Long)

  def deleteUser(id:Long)

  def deleteCaregiver(id:Long)

  def deletePatient(id:Long)

  def deleteCarePlan(id:Long)

  def getCaregiver(id:Long): Caregiver
//-------------------------------------------------
  def getInstitution(id:Long): Institution

  def viewPatients(id:Long): Patient

  def viewAllPatient(): List[PatientRepo#TableElementType]

  def getUser(id:Long) : User
}
