package controllers

import domain.{Diagnosis, Adherence, CarePlan, Patient}
import play.api.libs.json._
import play.api.mvc._
import services.PatientService
import services.impl.PatientServiceImpl

/**
 * Created by phakama on 2014/10/02.
 */
object PatientController extends Controller{

  val patientservice : PatientService = new PatientServiceImpl

  //implicit val patient = Json.writes[Patient]
  //implicit val plan = Json.writes[CarePlan]

  implicit val diag = Json.format[Diagnosis]
  implicit val adh = Json.format[Adherence]

  def getAdherence(id : Long) = Action{

    val json = Json.toJson(patientservice.getAdherence(id))
    Ok(json)
  }

  def geDiagnosis(id: Long) = Action{

    val json = Json.toJson(patientservice.getDiagnosis(id))
    Ok(json)
  }
}
