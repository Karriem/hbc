package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class QuestionAnswer (
                            question:String,
                            answer:Option[String],
                            diagnosisId:Long
                            )

object QuestionsAnswers{
  implicit lazy val questionAnswerFmt = Json.format[QuestionAnswer]
}
