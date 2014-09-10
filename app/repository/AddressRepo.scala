package repository

import domain.Address
import repository.CaregiverModel.CaregiverRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object AddressModel {

  class AddressRepo(tag:Tag) extends Table[Address](tag, "ADDRESS"){

      def streetAddress = column[String]("STREET_ADDRESS")
      def postalAddress = column[String]("POSTAL_ADDRESS")
      def postalCode = column[String]("POSTAL_CODE")
      def personId = column[Option[Long]]("CONTACT_PERSON_ID")
      def instituteId = column[Option[Long]]("INSTITUTE_ID")
      def patientId = column[Option[Long]]("PATIENT_ID")
      def caregiverId = column[Option[Long]]("CAREGIVER_ID")
      def coordinatorId = column[Option[Long]]("COORDINATOR_ID")
      def * = (streetAddress, postalAddress, postalCode, personId, instituteId, patientId, caregiverId, coordinatorId) <> (Address.tupled, Address.unapply)

      def contactPerson = foreignKey("PERSON_FK", personId, TableQuery[ContactPersonRepo])(_.personId)
      def coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
      def patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      def caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
      def institute = foreignKey("INSTITUTE_FK", instituteId, TableQuery[InstitutionRepo])(_.instituteId)

  }


}