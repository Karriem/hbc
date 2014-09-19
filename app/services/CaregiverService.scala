package services

import domain.Patient
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import repository.UserModel.UserRepo

/**
 * Created by karriem on 9/19/14.
 */
trait CaregiverService {

    def getCareplan(id:Long) : List[CarePlanRepo#TableElementType]

    def getPatientDetails(id:Long): List[PatientRepo#TableElementType]

    def getUserDetails(id:Long): List[UserRepo#TableElementType]

    def addPatient(patient : Patient)
}
