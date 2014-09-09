package repository

import domain.Referral
import repository.MonthlyReportModel.MonthlyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object ReferralModel {

  class ReferralRepo(tag:Tag) extends Table[Referral](tag, "REFERRAL"){

      def referralId = column[Long]("REFERRAL_ID", O.PrimaryKey, O.AutoInc)
      def referralDate = column[String]("REFERRAL_DATE")
      def monthlyReportId = column[Option[Long]]("MONTHLY_REPORT_ID")
      def * = (referralId, referralDate, monthlyReportId) <> (Referral.tupled, Referral.unapply)

      def monthlyReport = foreignKey("MONTHLYREPORT_FK", monthlyReportId, TableQuery[MonthlyReportRepo])(_.monthlyReportId)
  }

}
