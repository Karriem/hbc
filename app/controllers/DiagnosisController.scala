package controllers

import java.util.StringTokenizer

import domain.{QuestionAnswer, Disease, Diagnosis}
import model.DiagnosisModel
import org.joda.time.DateTime
import play.api.mvc._
import play.api.libs.json.{JsError, Writes, Json}
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.DiagnosisService
import services.impl.DiagnosisServiceImpl
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tonata on 10/2/14.
 */
object DiagnosisController extends Controller {

  val diagnosisService : DiagnosisService = new DiagnosisServiceImpl()

  implicit val diagnosisWrites = Json.writes[Diagnosis]
  implicit val diseaseWrites = Json.writes[Disease]
  implicit val qAWrites = Json.writes[QuestionAnswer]

  def createDiagnosis(dia: String
              ) = Action.async(parse.json) {
  request =>

    val input = request.body
    println(input)
    //val list = Json.fromJson[List[String]](input).get

    val o = (input \ "list").as[String]
    val dia = (input \ "diagnosis").as[String]

    var theList = new ListBuffer[QuestionAnswerRepo#TableElementType]()

    val token = new StringTokenizer(o, "#")

    while (token.hasMoreTokens){
      val qaS = token.nextToken()
      val t = new StringTokenizer(qaS, "(,)")

      t.nextToken()
      val question = t.nextToken()
      val answer = t.nextToken()
      val id = t.nextToken()

      val obj = QuestionAnswer(question, Some(answer), id.toLong)
      println(obj)
      theList += obj

    }
    val json = Json.parse(dia)

    val diagnosisObj = Json.fromJson[DiagnosisModel](json).get
    val diagnosisDom = diagnosisObj.getDomain()

    val dObj = Diagnosis(diagnosisDom.diagnosisId, diagnosisDom.diagnosisType,
                         diagnosisDom.treatment, diagnosisDom.followUpDate, diagnosisDom.dailyReportId,
                          diagnosisDom.eventType)
    val results : Future[Long] = Future{diagnosisService.createDiagnosis(dObj, theList.toList)}

    results.map( res =>
      Ok(Json.toJson(res)

      )
    )
  }

  def getDiagnosis(id: Long) = Action {
    val diagnosis = diagnosisService.getDiagnosis(id)
    val json = Json.toJson(diagnosis)
    Ok(json)
  }

  def getDisease(id: Long) = Action {
    val disease = diagnosisService.getDisease(id)
    val json = Json.toJson(disease)
    Ok(json)
  }

  def diagnosisPerCaregiver(id: Long) = Action {
    val diagnosises = diagnosisService.getAllDiagnosisByCaregiver(id)
    val json = Json.toJson(diagnosises)
    Ok(json)
  }

}
