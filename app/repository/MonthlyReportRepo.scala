package repository

import java.util.Date

import domain.MonthlyReport

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object MonthlyReportModel {

  class MonthlyReportRepo(tag:Tag) extends Table[MonthlyReport](tag, "MONTHLYREPORT"){

      def monthlyReportId = column[Long]("MONTHLY_REPORT_ID", O.PrimaryKey, O.AutoInc)
      def visits = column[Int]("VISITS")
      def date = column[Date]("MONTH_DATE")
      def * = (monthlyReportId, date, visits) <> (MonthlyReport.tupled, MonthlyReport.unapply)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))

  }

}
