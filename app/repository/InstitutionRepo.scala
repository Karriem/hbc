package repository

import domain.Institution
import repository.CoordinatorModel.CoordinatorRepo
import repository.ReferralModel.ReferralRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object InstituteModel {

  class InstitutionRepo(tag:Tag) extends Table[Institution](tag, "INSTITUTION"){

    def instituteId = column[Long]("INSTITUTE_ID", O.PrimaryKey, O.AutoInc)
    def instituteType = column[String]("INSTITUTE_TYPE")
    def instituteName = column[String]("INSTITUTE_NAME")
    def coordinatorId = column[Option[Long]]("COORDINATOR")
    def referralId = column[Long]("REFERRAL_ID")
    def * = (instituteId, instituteType, instituteName, coordinatorId, referralId) <> (Institution.tupled, Institution.unapply)

    def coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
    def referral = foreignKey("REFERRAL_FK", referralId, TableQuery[ReferralRepo])(_.referralId)
  }

}
