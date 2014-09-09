package repository

import domain.Schedule

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
  }

}