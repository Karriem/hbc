package controllers

import domain.QuestionAnswer
import model.QuestionAnswerModel
import play.api.libs.json.Json
import play.api.mvc._
import services.QuestionAnswerService
import services.impl.QuestionAnswerServiceImpl

import scala.concurrent.Future

/**
 * Created by tonata on 10/8/14.
 */
object QuestionAnswerController extends Controller{

  val questionAnswer: QuestionAnswerService = new QuestionAnswerServiceImpl()

  implicit val qAWrites = Json.writes[QuestionAnswer]

 /* def createQuestionAnswer(qA: List[String]
                             ) = Action.async(parse.json) {
      request=>

      val input = request.body
        val qA = Json.fromJson[QuestionAnswerModel](input).get
        val qAObj = qA.getDomain()

        val results : Future[Long] = Future{questionAnswer.createQuestionAndAnswers()}

        results.map(res =>
          Ok(Json.toJson(res)))
  }*/

  def getQuestionAnswer(id: Long)= Action {

    val qA = questionAnswer.getQuestionAndAnswers(id)
    val json = Json.toJson(qA)
    Ok(json)
  }

}
