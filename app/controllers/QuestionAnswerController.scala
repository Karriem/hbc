package controllers

import domain.QuestionAnswer
import play.api.libs.json.Json
import play.api.mvc._
import services.QuestionAnswerService
import services.impl.QuestionAnswerServiceImpl

/**
 * Created by tonata on 10/8/14.
 */
object QuestionAnswerController extends Controller{

  val questionAnswer: QuestionAnswerService = new QuestionAnswerServiceImpl()

  implicit val qAWrites = Json.writes[QuestionAnswer]

  /*def createQuestionAnswer(diagnosis: String,
                             ) = Action {

  }*/

  def getQuestionAnswer(id: Long)= Action {

    val qA = questionAnswer.getQuestionAndAnswers(id)
    val json = Json.toJson(qA)
    Ok(json)
  }

}
