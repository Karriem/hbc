package repository

import java.util.Date

import domain.Measurement
import repository.CaregiverModel.CaregiverRepo
import repository.PatientModel.PatientRepo
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 2014/10/08.
 */
object MeasurementModel {

  class MeasurementRepo(tag: Tag) extends Table[Measurement] (tag, "MEASUREMENT"){

    def measurementID = column[Long]("MEASUREMENT_ID", O.PrimaryKey, O.AutoInc)
    def dateTaken = column[Date]("DATE_TAKEN")
    def weight = column[Double]("WEIGHT")
    def bloodPressure = column[Double]("BLOOD_PRESSURE")
    def temperature = column[Double]("TEMPERATURE")
    def patientID = column[Long]("PATIENT_ID")
    def caregiverID = column[Long]("CAREGIVER_ID")

    val caregiver = foreignKey("CAREGIVER_FK", caregiverID, TableQuery[CaregiverRepo])(_.caregiverId)
    val patient = foreignKey("PATIENT_FK", patientID, TableQuery[PatientRepo])(_.patientId)

    def * = (measurementID,dateTaken, weight, bloodPressure, temperature, patientID, caregiverID ) <> (Measurement.tupled, Measurement.unapply)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }

}
