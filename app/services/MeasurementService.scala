package services

import domain.Measurement
import repository.MeasurementModel.MeasurementRepo

/**
 * Created by tonata on 2014/10/08.
 */
trait MeasurementService {

  def createMeasurement (measurement: Measurement): Long

  def getMeasurements(id: Long): List[MeasurementRepo#TableElementType]

  //def updateMeasurement(measurement: Measurement)

}
