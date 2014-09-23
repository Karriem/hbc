package services.impl

import domain.{MonthlyReport, Referral}
import repository.DailyReportModel.DailyReportRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.ReferralModel.ReferralRepo
import services.MonthlyReportService

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/18/14.
 */
class MonthlyReportServiceImpl extends MonthlyReportService {

  val monthlyReportREpo = TableQuery[MonthlyReportRepo]
  val referralRepo = TableQuery[ReferralRepo]
  val dailyReportRepo = TableQuery[DailyReportRepo]

  override def createMonthlyReport(report: MonthlyReport, referral: Referral, dailyReportid: List[Long]): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val visits = dailyReportid.length
      val updatedMReport = MonthlyReport(report.monthlyReportId , report.date, visits)
      val mID = monthlyReportREpo.returning(monthlyReportREpo.map (_.monthlyReportId)).insert(updatedMReport)

      val updatedReferral = Referral(referral.referralId, referral.referralDate, Option(mID))
      val rID = referralRepo.returning(referralRepo.map (_.referralId)).insert(updatedReferral)

      dailyReportid foreach { case (id: Long) =>
        dailyReportRepo.filter(_.dailyReportId === id).map(_.monthlyReportId).update(Option(mID))
      }

      return mID
    }
  }

  override def getTotalVisits(id: Long): Int ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return monthlyReportREpo.filter(_.monthlyReportId === id).map(_.visits).list.head
    }
  }

  override def checkForReferral(id: Long): Referral = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return referralRepo.filter(_.monthlyReportId === id).list.head
    }
  }

  override def getAllDailyReports(id: Long) : List[DailyReportRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return dailyReportRepo.filter(_.monthlyReportId === id).list
    }
  }

  /*override def getMonthlyReport(theDate: String) : MonthlyReport = {
    /*Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      return monthlyReportREpo.filter(_.date === theDate).list.head
    }*/
  }*/

}
