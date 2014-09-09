package repository

import java.sql.Date

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

      def patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
      def coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
  }

}