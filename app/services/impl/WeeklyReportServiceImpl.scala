package services.impl

import domain.{WeeklyReport, MonthlyReport, Referral}
import repository.DailyReportModel.DailyReportRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.ReferralModel.ReferralRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import services.WeeklyReportService

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/18/14.
 */
class WeeklyReportServiceImpl extends WeeklyReportService {

  val weeklyReportREpo = TableQuery[WeeklyReportRepo]
  val referralRepo = TableQuery[ReferralRepo]
  val dailyReportRepo = TableQuery[DailyReportRepo]

  override def createWeeklyReport(report: WeeklyReport, referral: Referral, dailyReportid: List[Long]): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val visits = dailyReportid.length
      val updatedWReport = WeeklyReport(report.weeklyReportId , report.weekStartDate, report.weekEndDate, report.discharges, visits, report.monthlyReportID)
      val wID = weeklyReportREpo.returning(weeklyReportREpo.map (_.weeklyReportId)).insert(updatedWReport)

      val updatedReferral = Referral(referral.referralId, referral.referralDate, Option(wID))
      val rID = referralRepo.returning(referralRepo.map (_.referralId)).insert(updatedReferral)

      dailyReportid foreach { case (id: Long) =>
        dailyReportRepo.filter(_.dailyReportId === id).map(_.weeklyReportId).update(Option(wID))
      }

      return wID
    }
  }

  override def getTotalVisits(id: Long): Int ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return weeklyReportREpo.filter(_.weeklyReportId === id).map(_.visits).list.head
    }
  }

  override def checkForReferral(id: Long): Referral = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return referralRepo.filter(_.weeklyReportId === id).list.head
    }
  }

  override def getAllDailyReports(id: Long) : List[DailyReportRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return dailyReportRepo.filter(_.weeklyReportId === id).list
    }
  }

  /*override def getWeeklyReport(theDate: Date) : WeeklyReport = {
    /*Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return monthlyReportREpo.filter(_.date === theDate).list.head
    }*/
  }*/

}
