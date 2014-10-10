package controllers

import domain.{CarePlan, User, Patient, Caregiver}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.CaregiverService
import services.impl.CaregiverServiceImpl

/**
 * Created by karriem on 10/3/14.
 */
object CaregiverController extends Controller{

  val caregiverServ : CaregiverService = new CaregiverServiceImpl

  implicit val caregiverWrites = Json.writes[Caregiver]
  implicit val carePlanWrites = Json.writes[CarePlan]
  implicit val patientWrites = Json.writes[Patient]
  implicit val userWrites = Json.writes[User]

  def getCarePlan(id:Long) = Action {

    val careplan = caregiverServ.getCareplan(id)
    val json = Json.toJson(careplan)
    Ok(json)
  }

  def getPatient(id:Long) = Action {

    val patient =  caregiverServ.getPatientDetails(id)
    val json = Json.toJson(patient)
    Ok(json)
  }

  def getUser(id:Long) = Action {

    val user = caregiverServ.getUserDetails(id)
    val json = Json.toJson(user)
    Ok(json)
  }
}
