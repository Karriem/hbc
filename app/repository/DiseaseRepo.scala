package repository

import domain.Disease
import repository.DiagnosisModel.DiagnosisRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object DiseaseModel {

  class DiseaseRepo(tag:Tag) extends Table[Disease](tag, "DISEASE"){

      def diseaseId = column[Long]("DISEASE_ID", O.PrimaryKey, O.AutoInc)
      def diseaseType = column[String]("DISEASE_TYPE")
      def symptoms = column[String]("SYMPTOMS_DESCRIPTION")
      def diagnosisId = column[Long]("DIAGNOSIS_ID")
      def * = (diseaseId, diseaseType, symptoms, diagnosisId) <> (Disease.tupled, Disease.unapply)

      val diagnosis = foreignKey("DIAGNOSIS_FK", diagnosisId, TableQuery[DiagnosisRepo])(_.diagnosisId)
  }

}
