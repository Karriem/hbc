package services.impl

import domain.MedicalSummary
import repository.CaregiverModel.CaregiverRepo
import repository.MeasurementModel.MeasurementRepo
import repository.PatientModel.PatientRepo
import services.MedicalSummaryService

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by tonata on 2014/10/08.
 */
class MedicalSummaryServiceImpl extends MedicalSummaryService {

  val measureRepo = TableQuery[MeasurementRepo]
  val care = TableQuery[CaregiverRepo]
  val pat = TableQuery[PatientRepo]


  override def getMeasurements(id: Long): List[MeasurementRepo#TableElementType] = {
    // parameter id - patientID
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return measureRepo.filter(_.patientID === id).list
    }
  }

  override def createMeasurement(measurement: MedicalSummary): Long ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return measureRepo.returning(measureRepo.map(_.measurementID)).insert(measurement)
    }
  }







}
