package services.impl

import domain.Measurement
import repository.CaregiverModel.CaregiverRepo
import repository.MeasurementModel.MeasurementRepo
import repository.PatientModel.PatientRepo
import services.MeasurementService

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by tonata on 2014/10/08.
 */
class MeasurementServiceImpl extends MeasurementService {

  val measureRepo = TableQuery[MeasurementRepo]
  val care = TableQuery[CaregiverRepo]
  val pat = TableQuery[PatientRepo]

  override def createMeasurement(measurement: Measurement,
                                 patientID: Long,
                                 caregiverID: Long): Long ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      val measurementRecord = Measurement(measurement.measurementID, measurement.dateTaken,
        measurement.weight, measurement.bloodPressure, measurement.temperature, patientID,
        caregiverID)
      val mID = measureRepo.returning(measureRepo.map(_.measurementID)).insert(measurementRecord)

      return mID
    }

  }

  override def getMeasurements(id: Long): List[MeasurementRepo#TableElementType] = {
    // parameter id - patientID
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return measureRepo.filter(_.patientID === id).list
    }
  }


}
