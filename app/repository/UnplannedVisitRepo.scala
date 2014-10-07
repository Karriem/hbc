package repository

import java.util.Date
import domain.UnplannedVisit
import repository.CaregiverModel.CaregiverRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 10/7/14.
 */
object UnplannedVisitModel {

  class UnplannedVisitRepo (tag:Tag) extends Table[UnplannedVisit](tag, "UNPLANNEDVISIT"){

    def unplannedVisitID = column[Long]("UNPLANNED_VISIT_ID",O.PrimaryKey, O.AutoInc)
    def visitDate = column[Date]("VISIT_DATE")
    def patientName = column[String]("PATIENT_NAME")
    def patientLastName = column[String]("PATIENT_LASTNAME")
    def caregiverID = column[Long]("CAREGIVER_ID")

    def * = (unplannedVisitID, visitDate, patientName, patientLastName, caregiverID) <> (UnplannedVisit.tupled, UnplannedVisit.unapply)

    val caregiver = foreignKey("CAREGIVER_FK", caregiverID, TableQuery[CaregiverRepo])(_.caregiverId)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))

  }

}
