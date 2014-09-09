package repository

import domain.Visit
import repository.CarePlanModel.CarePlanRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/9/14.
 */
object VisitModel {

  class VisitRepo(tag:Tag) extends Table[Visit](tag, "VISIT"){

      def visitId = column[Long]("VISIT_ID", O.PrimaryKey, O.AutoInc)
      def nextVisit = column[String]("NEXT_VISIT")
      def carePlanId = column[Long]("CAREPLAN_ID")
      def * = (visitId, nextVisit, carePlanId) <> (Visit.tupled, Visit.unapply)

      def carePlan = foreignKey("CAREPLAN_FK", carePlanId, TableQuery[CarePlanRepo])(_.planId)
  }

}
