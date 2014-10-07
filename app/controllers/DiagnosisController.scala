package controllers

import domain.{QuestionAnswer, Disease, Diagnosis}
import play.api.mvc._
import play.api.libs.json.{JsError, Writes, Json}
import services.DiagnosisService
import services.impl.DiagnosisServiceImpl
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

  /*def create(diagnosis: String, disease: String, qA: String) = Action.async(parse.json) {
  request =>
    val input = request.body

    val diag = Json.fromJson[Diagnosis](input).get
    val di = Json.fromJson[Disease](input).get
    val qa = Json.fromJson[QuestionAnswer](input).get

    val diagObj = diag.copy(diagnosis.toLong)
    val diseaseObj = di.copy(disease.toLong)
    val qaObj = qa.copy(qA)

    val results : Future[Long] =  Future{diagnosisService.createDiagnosis(diagObj, diseaseObj, qaObj)}

    results.map( result =>
      Ok(Json.toJson(diagObj),
      Json.toJson(diseaseObj),
      Json.toJson(qaObj)
      )
    )
  }*/

  def getDiagnosis(id: Long) ={
  }

  def getDiesease(id: Long) = {
  }

  def diagnosisPerCaregiver(id: Long) = {
  }

  def index = Action {
    Ok//(views.html.index("Your new application is ready."))
  }
}
