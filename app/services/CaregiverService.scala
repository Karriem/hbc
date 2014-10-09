package services

import domain.{CarePlan, Patient, User}

/**
 * Created by karriem on 9/19/14.
 */
trait CaregiverService {

    def getCareplan(id:Long) : CarePlan

    def getPatientDetails(id:Long): Patient

    def getUserDetails(id:Long): User
}
