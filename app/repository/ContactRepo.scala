package repository

import domain.Contact
import repository.CaregiverModel.CaregiverRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo
import repository.UnplannedVisitModel.UnplannedVisitRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object ContactModel {

  class ContactRepo(tag:Tag) extends Table[Contact](tag, "CONTACT"){

    def homeTel = column[Option[String]]("HOMETEL")
    def cellNumber = column[String]("CELLNUMBER")
    def email = column[String]("EMAIL")
    def personId = column[Option[Long]]("PERSON_ID")
    def instituteId = column[Option[Long]]("INSTITUTE_ID")
    def coordinatorId = column[Option[Long]]("COORDINATOR_ID")
    def patientId = column[Option[Long]]("PATIENT_ID")
    def caregiverId = column[Option[Long]]("CAREGIVER_ID")
    def unplanned_Visit = column[Option[Long]]("UNPLANNEDVISIT_ID")
    def * = (homeTel, cellNumber, email, personId, instituteId, coordinatorId, patientId, caregiverId, unplanned_Visit) <> (Contact.tupled, Contact.unapply)

    val contactPerson = foreignKey("PERSON_FK", personId, TableQuery[ContactPersonRepo])(_.personId)
    val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
    val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
    val caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
    val institute = foreignKey("INSTITUTE_FK", instituteId, TableQuery[InstitutionRepo])(_.instituteId)
    val unplannedVisit = foreignKey("UNPLANNEDVISIT_FK", instituteId, TableQuery[UnplannedVisitRepo])(_.unplannedVisitID)
  }

}