package services

import domain._
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo

/**
 * Created by tonata on 9/18/14.
 */
trait DailyReportService {

  def createDailyReport(report: DailyReport,
                         timesheet: TimeSheet,
                         category: Category,
                         caregiver: Caregiver,
                         patient: Patient,
                         diagnosisID: Long): Long

  def getTimeSheetDetails(id: Long): TimeSheet

  def getCategory(id: Long): Category

  def getDiagnosis(id: Long) : List[DiagnosisRepo#TableElementType]

  def getReportByPatient(id: Long) : List[DailyReportRepo#TableElementType]

  def getReportByCaregiver(id: Long): List[DailyReportRepo#TableElementType]

  def getAllReports(): List[DailyReportRepo#TableElementType]

}
