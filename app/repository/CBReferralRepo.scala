package repository

import java.util.Date

import domain.CBReferral
import repository.PatientModel.PatientRepo
import repository.ScreeningModel.ScreeningRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 11/12/14.
 */
object CBReferralModel {

  class CBReferralRepo(tag:Tag) extends Table[CBReferral](tag,"CBREFERRAL"){

    def cbReferralId = column[Long]("CB_REFERRAL_ID", O.AutoInc, O.PrimaryKey)
    def place = column[String]("PLACE")
    def patientId = column[Long]("PATIENT_ID")
    def dateTaken = column[Date]("DATE_TAKEN")
    def healthCondition = column[String]("HEALTH_CONDITION")
    def screeningId = column[Long]("SCREENING_ID")
    def reading = column[String]("READING")
    def action = column[String]("ACTION")
    def * = (cbReferralId, place, patientId, dateTaken, healthCondition, screeningId, reading, action) <> (CBReferral.tupled, CBReferral.unapply)

    val patient = foreignKey("PATIENT_FK", patientId, TableQuery[PatientRepo])(_.patientId)
    val screening = foreignKey("SCREENING_FK", screeningId, TableQuery[ScreeningRepo])(_.screeningId)

    implicit val JavaUtilDateMapper =
      MappedColumnType .base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.util.Date(d.getTime))
  }
}
