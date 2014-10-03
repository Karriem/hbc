package controllers

import akka.io.Tcp.Write
import domain.{CarePlan, Patient}
import play.api.libs.json.{JsError, Writes, Json}
import play.api.mvc.{BodyParsers, Action}
import play.mvc._
import services.PatientService
import services.impl.PatientServiceImpl
import spray.can.parsing.Result.Ok

/**
 * Created by phakama on 2014/10/02.
 */
object PatientController extends Controller{

  val patientservice : PatientService = new PatientServiceImpl

  //implicit val patient = Json.writes[Patient]
  //implicit val plan = Json.writes[CarePlan]

  implicit val pat = Json.format[Patient]
  implicit val plan = Json.format[CarePlan]


  def getPlan(id : Long) = Action(BodyParsers.parse.json){//request =>
    //val patient = request.body.validate[Patient]
    val patient = patientservice.displayCarePlan(id)
    val patRecord = Json.toJson(patient)
   Ok//(patRecord)
  }
}
