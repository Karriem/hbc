package model

import domain.QuestionAnswer
import play.api.libs.json.Json

/**
 * Created by tonata on 10/10/14.
 */
case class QuestionAnswerModel (question:String,
                                answer:String,
                                diagnosisId:Long
                                ){
  def getDomain(): QuestionAnswer = QuestionAnswerModel.domain(this) }


object QuestionAnswerModel {

  implicit lazy val questionAnswerFmt = Json.format[QuestionAnswerModel]

  def domain(model: QuestionAnswerModel ) ={
    //var value : String = ""

    /*if (model.answer == " "){
      value
    }*/
    QuestionAnswer(model.question,
                  Some(model.answer),
                  model.diagnosisId)
  }
}
