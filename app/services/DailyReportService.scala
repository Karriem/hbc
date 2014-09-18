package services

import domain.{Category, Diagnosis, DailyReport}

/**
 * Created by tonata on 9/18/14.
 */
trait DailyReportService {

  def createDailyReport(report: DailyReport)

  def getTimeSheetDetails(id: Long)

  def getCategory(id: Long): Category

  def getDiagnosis(id: Long) // list of diagnosis' to be returned

  def getReportByPatient(id: Long) : DailyReport

  def getReportByCaregiver(id: Long): DailyReport

  def getAllReports()

}
