package repository


import java.util.Date

import domain.CarePlan
import repository.CoordinatorModel.CoordinatorRepo
import repository.PatientModel.PatientRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object CarePlanModel {

  class CarePlanRepo(tag:Tag) extends Table[CarePlan](tag, "CAREPLAN"){

      def planId = column[Long]("CAREPLAN_ID", O.PrimaryKey, O.AutoInc)
      def description  = column[String]("DESCRIPTION")
      def startDate = column[Date]("START_DATE")
      def endDate = column[Date]("END_DATE")
      def patientId = column[Long]("PATIENT_ID")
      def coordinatorId = column[Long]("COORDINATOR_ID")
      def * = (planId, description, startDate, endDate, patientId, coordinatorId) <> (CarePlan.tupled, CarePlan.unapply)

      val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}