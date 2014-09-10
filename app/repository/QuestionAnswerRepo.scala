package repository

import domain.QuestionAnswer
import repository.DiagnosisModel.DiagnosisRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */
object QuestionAnswerModel {

  class QuestionAnswerRepo(tag:Tag) extends Table[QuestionAnswer](tag, "QUESTION_ANSWER"){

      def question = column[String]("QUESTIONS")
      def answer = column[Option[String]]("ANSWERS")
      def diagnosisId = column[Long]("DIAGNOSIS")
      def * = (question, answer, diagnosisId) <> (QuestionAnswer.tupled, QuestionAnswer.unapply)

      val diagnosis = foreignKey("DIAGNOSIS_FK", diagnosisId, TableQuery[DiagnosisRepo])(_.diagnosisId)
  }

}
