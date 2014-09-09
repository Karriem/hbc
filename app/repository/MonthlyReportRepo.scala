package repository

import domain.MonthlyReport

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object MonthlyReportModel {

  class MonthlyReportRepo(tag:Tag) extends Table[MonthlyReport](tag, "MONTHLYREPORT"){

      def monthlyReportId = column[Long]("MONTHLY_REPORT_ID", O.PrimaryKey, O.AutoInc)
      def visits = column[Int]("VISITS")
      def * = (monthlyReportId, visits) <> (MonthlyReport.tupled, MonthlyReport.unapply)

  }

}
