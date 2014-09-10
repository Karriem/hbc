package repository

import domain.Schedule
import repository.CaregiverModel.CaregiverRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/9/14.
 */
object ScheduleModel {

  class ScheduleRepo(tag:Tag) extends Table[Schedule](tag, "SCHEDULE"){

      def scheduleId = column[Long]("SCHEDULE_ID", O.PrimaryKey, O.AutoInc)
      def patientId = column[Long]("PATIENT_ID")
      def caregiverId = column[Long]("CAREGIVER_ID")
      def * = (scheduleId, patientId, caregiverId) <> (Schedule.tupled, Schedule.unapply)

      val caregiverFK = foreignKey("CAR_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
      val patientFK = foreignKey("PAT_FK", patientId, TableQuery[PatientRepo])(_.patientId)

  }

}