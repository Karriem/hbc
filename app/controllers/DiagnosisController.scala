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

    val diag = Json.fromJson[DiagnosisModel](input).get
    val di = Json.fromJson[Disease](input).get
    val qa = Json.fromJson[QuestionAnswer](input).get

    val diagObj = diag.copy(diagnosisId = diagnosis.toLong)
    val diseaseObj = di.copy(diseaseId=disease.toLong)
    val qaObj = qa.copy(qA)

    val results : Future[Long] =  Future{diagnosisService.createDiagnosis(diagObj, diseaseObj, qaObj)}

    results.map( result =>
      Ok(Json.toJson(diagObj),
      Json.toJson(diseaseObj),
      Json.toJson(qaObj)
      )
    )
  }*/

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
