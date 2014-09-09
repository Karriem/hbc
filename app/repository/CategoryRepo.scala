package repository

import domain.Category
import repository.DailyReportModel.DailyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object CategoryModel {

  class CategoryRepo(tag:Tag) extends Table[Category](tag, "CATEGORY"){

      def description = column[String]("DESCRIPTION")
      def level = column[String]("LEVEL")
      def dailyReportId = column[Long]("DAILY_REPORT_ID")
      def * = (description, level, dailyReportId) <> (Category.tupled, Category.unapply)

      def dailyReport = foreignKey("DAILYREPORT_FK", dailyReportId, TableQuery[DailyReportRepo])(_.dailyReportId)
  }

}