package controllers

import java.util.StringTokenizer

import domain.QuestionAnswer
import model.QuestionAnswerModel
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.QuestionAnswerService
import services.impl.QuestionAnswerServiceImpl
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

/**
 * Created by tonata on 10/8/14.
 */
object QuestionAnswerController extends Controller{

  val questionAnswer: QuestionAnswerService = new QuestionAnswerServiceImpl()

  implicit val qAWrites = Json.writes[QuestionAnswer]

 def createQuestionAnswer(js: String
                             ) = Action.async(parse.json) {
      request=>

        val input = request.body
        println(input)
        val qA = Json.fromJson[List[String]](input).get
        println(qA{0})

        var theList = new ListBuffer[QuestionAnswerRepo#TableElementType]()
        val token = new StringTokenizer(qA{0}, "#")
        //var qaS = token.nextToken()
        while (token.hasMoreTokens){
          val qaS = token.nextToken()
          println(qaS)
          val t = new StringTokenizer(qaS, "(,)")
          t.nextToken()
          val question = t.nextToken()
          val answer = t.nextToken()
          val id = t.nextToken()

          val obj = QuestionAnswer(question, Some(answer), id.toLong)
          println(obj)
          theList += obj
         // qaS = token.nextToken()
        }

        //val qaS = token.nextToken()
        /*val t = new StringTokenizer(qaS, "(,)")
        println(t.nextToken())
        println(t.nextToken())
        println(t.nextToken())
        println(t.nextToken())*/
        //val qAObj = qA.getDomain()

        val results : Future[Long] = Future{questionAnswer.createQuestionAndAnswers(theList.toList)}

        results.map(res =>
         Ok(Json.toJson(res)))
  }

  def getQuestionAnswer(id: Long)= Action {

    val qA = questionAnswer.getQuestionAndAnswers(id)
    val json = Json.toJson(qA)
    Ok(json)
  }

}
