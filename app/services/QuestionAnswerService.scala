package services

import domain.{QuestionAnswer, Diagnosis}
import repository.QuestionAnswerModel.QuestionAnswerRepo

/**
 * Created by tonata on 9/30/14.
 */
trait QuestionAnswerService {

  def createQuestionAndAnswers(diagnosis: Diagnosis,
                               questionAnswerList: List[QuestionAnswerRepo#TableElementType]): Long
  def getQuestionAndAnswers(id: Long): List[QuestionAnswerRepo#TableElementType]


}
