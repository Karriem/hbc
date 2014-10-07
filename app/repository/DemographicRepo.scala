package repository

import java.util.Date

import domain.Demographic
import repository.CaregiverModel.CaregiverRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object DemographicModel {

  class DemographicRepo(tag:Tag) extends Table[Demographic](tag, "DEMOGRAPHIC"){

    def age = column[Int]("AGE")
    def gender = column[String]("GENDER")
    def dateOfBirth = column[Date]("DATE_OF_BIRTH")
    def coordinatorId = column[Option[Long]]("COORDINATOR_ID")
    def personId = column[Option[Long]]("PERSON_ID")
    def patientId = column[Option[Long]]("PATIENT_ID")
    def caregiverId = column[Option[Long]]("CAREGIVER_ID")
    def * = (age, gender, dateOfBirth, coordinatorId, personId, patientId, caregiverId) <> (Demographic.tupled, Demographic.unapply)

    val contactPerson = foreignKey("PERSON_FK", personId, TableQuery[ContactPersonRepo])(_.personId)
    val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
    val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
    val caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}
