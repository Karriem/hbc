package repository

import java.util.Date
import domain.WeeklyReport
import repository.MonthlyReportModel.MonthlyReportRepo
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 10/7/14.
 */
object WeeklyReportModel {

  class WeeklyReportRepo (tag: Tag) extends Table[WeeklyReport](tag, "WEEKLYREPORT"){

    def weeklyReportId = column[Long]("WEEKLYREPORTID", O.PrimaryKey, O.AutoInc)
    def weekStartDate = column[Date]("WEEK_START_DATE")
    def weekEndDate = column[Date]("WEEK_END_DATE")
    def discharges = column[String]("DISCHARGES")
    def visits = column[Int]("VISITS")
    def monthlyReportID = column[Option[Long]]("MONTHLY_REPORT_ID")

    def * = (weeklyReportId, weekStartDate, weekEndDate, discharges, visits, monthlyReportID ) <> (WeeklyReport.tupled, WeeklyReport.unapply)

    val monthly = foreignKey("MONTHLY_REPORT_FK", monthlyReportID, TableQuery[MonthlyReportRepo])(_.monthlyReportId)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))

  }

}
