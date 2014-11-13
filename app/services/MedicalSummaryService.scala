package services

import domain.MedicalSummary
import repository.MedicalSummaryModel.MedicalSummaryRepo

/**
 * Created by tonata on 2014/10/08.
 */
trait MedicalSummaryService {

  def createMeasurement (measurement: MedicalSummary): Long

  def getMeasurements(id: Long): List[MedicalSummaryRepo#TableElementType]

  //def updateMeasurement(measurement: Measurement)

}
