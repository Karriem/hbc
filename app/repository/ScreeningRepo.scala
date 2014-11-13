package repository

import domain.Screening
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 11/12/14.
 */
object ScreeningModel {

  class ScreeningRepo(tag:Tag) extends Table[Screening](tag, "SCREENING"){

    def screeningId = column[Long]("SCREENING_ID", O.PrimaryKey, O.AutoInc)
    def screener = column[String]("SCREENER")
    def description = column[String]("DESCRIPTION")
    def * = (screeningId, screener, description) <> (Screening.tupled, Screening.unapply)
  }
}
