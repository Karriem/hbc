package services

import domain.Patient

/**
 * Created by karriem on 9/19/14.
 */
trait CaregiverService {

    def getCareplan(id:Long)

    def getPatientDetails(id:Long)

    def getUserDetails(id:Long)

    def addPatient(patient : Patient)
}
