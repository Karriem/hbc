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
      def hbcPackage = column[String]("HBC_PACKAGE")
      def score = column[Int]("SCORE")
      def * = (description, level, dailyReportId, hbcPackage, score) <> (Category.tupled, Category.unapply)

      val dailyReport = foreignKey("DAILYREPORT_FK", dailyReportId, TableQuery[DailyReportRepo])(_.dailyReportId)
  }

}