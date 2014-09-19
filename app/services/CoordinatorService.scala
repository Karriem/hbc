package services

import domain._
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo

/**
 * Created by karriem on 9/18/14.
 */
trait CoordinatorService {

  def getInstitution(id:Long): List[InstitutionRepo#TableElementType]

  def viewPatients(id:Long): List[PatientRepo#TableElementType]

  def viewAllPatient(): List[PatientRepo#TableElementType]

  def createUser(user : User)

  def getUser(id:Long) : List[UserRepo#TableElementType]

  def addCoordinator(co : Coordinator)

  def createCarePlan(care : CarePlan)

  def addCareGiver(giver : Caregiver)

  def addPatient(pat : Patient)
}
