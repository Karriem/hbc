package services

import domain.{WeeklyReport, MonthlyReport, Referral}
import repository.DailyReportModel.DailyReportRepo

/**
 * Created by tonata on 9/18/14.
 */
trait WeeklyReportService {

  def createWeeklyReport(report: WeeklyReport,
                           referral: Referral,
                           dailyReportid: List[Long]) : Long

  def getTotalVisits(id: Long): Int

  def checkForReferral(id: Long): Referral

  def getAllDailyReports(id: Long) : List[DailyReportRepo#TableElementType]

  //def getMonthlyReport(theDate: String) : MonthlyReport
}
