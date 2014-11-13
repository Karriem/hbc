package repository

import domain.ClientAssessment
import repository.ReferralModel.ReferralRepo

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by karriem on 11/13/14.
 */
object ClientAssessmentModel{

  class ClientAssessmentRepo(tag:Tag) extends Table[ClientAssessment](tag,"CLIENTASSESSMENT"){

    def activity = column[String]("ACTIVITY")
    def category = column[Int]("CATEGORY")
    def referralId = column[Long]("REFERRAL_ID")
    def * = (activity, category, referralId) <> (ClientAssessment.tupled, ClientAssessment.unapply)

    def referral = foreignKey("REFERRAL_FK", referralId, TableQuery[ReferralRepo])(_.referralId)
  }
}
