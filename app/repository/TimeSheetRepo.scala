package repository

import domain.TimeSheet
import repository.DailyReportModel.DailyReportRepo
import repository.ScheduleModel.ScheduleRepo
import repository.VisitModel.VisitRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/9/14.
 */
object TimeSheetModel {

  class TimeSheetRepo(tag:Tag) extends Table[TimeSheet](tag, "TIMESHEET"){

      def workDay = column[String]("WORKDAY")
      def timeIn = column[String]("TIME_IN")
      def timeOut = column[String]("TIME_OUT")
      def visitId = column[Option[Long]]("VISIT_ID")
      def dailyReportId = column[Option[Long]]("DAILY_REPORT_ID")
      def scheduleId = column[Option[Long]]("SCHEDULE_ID")
      def * = (workDay, timeIn, timeOut, visitId, dailyReportId, scheduleId) <> (TimeSheet.tupled, TimeSheet.unapply)

      val visit = foreignKey("VISIT_FK", visitId, TableQuery[VisitRepo])(_.visitId)
      val dailyReport = foreignKey("DAILYREPORT_FK", dailyReportId, TableQuery[DailyReportRepo])(_.dailyReportId)
      val schedule = foreignKey("SCHEDULE_FK", scheduleId, TableQuery[ScheduleRepo])(_.scheduleId)
  }

}
