package services

import domain.MedicalSummary
import repository.MeasurementModel.MeasurementRepo

/**
 * Created by tonata on 2014/10/08.
 */
trait MedicalSummaryService {

  def createMeasurement (measurement: MedicalSummary): Long

  def getMeasurements(id: Long): List[MeasurementRepo#TableElementType]

  //def updateMeasurement(measurement: Measurement)

}
