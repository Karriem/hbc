package controllers

import domain._
import model._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.CoordinatorService
import services.impl.CoordinatorServiceImpl
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by karriem on 10/3/14.
 */
object CoordinatorController extends Controller {

  val coorServ : CoordinatorService = new CoordinatorServiceImpl

  implicit val coordinatorWrites = Json.writes[Coordinator]
  implicit val institutionWrites = Json.writes[Institution]
  implicit val userWrites = Json.writes[User]
  implicit val patientWrites = Json.writes[Patient]
  implicit val carePlanWrites = Json.writes[CarePlan]
  implicit val caregiverWrites = Json.writes[Caregiver]

  def getInstitution(id:Long) = Action {

    val institution = coorServ.getInstitution(id)
    val json = Json.toJson(institution)
    Ok(json)
  }

  def getUser(id:String) = Action {

    val user = coorServ.getUser(id)
    val json = Json.toJson(user)
    Ok(json)
  }

  def checkCredentials(username:String, password:String) = Action {

    val id = coorServ.checkCredentials(username, password)
    val json = Json.toJson(id)
    Ok(id)
  }

  def createUser(user:String) = Action.async(parse.json){
    request =>
      val input = request.body
      println(input)
      val userModel = Json.fromJson[UserModel](input).get
      val userObj = userModel.getDomain()
      val u : Future[Long] = Future{coorServ.createUser(userObj)}

      u.map(use =>
        Ok(Json.toJson(use))
      )
  }

  def addCoordinator(coordinator:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val coor = Json.fromJson[CoordinatorModel](input).get
      val userObj = coor.getDomain()
      val co : Future[Long] = Future{coorServ.addCoordinator(userObj)}

      co.map(c =>
        Ok(Json.toJson(c))
      )
  }

  def getPatient(id:Long) = Action {

    val patient = coorServ.viewPatients(id)
    val json = Json.toJson(patient)
    Ok(json)
  }

  def getCaregiver(id:Long) = Action {

    val giver = coorServ.getCaregiver(id)
    val json = Json.toJson(giver)
    Ok(json)
  }

  def viewAllPatients = Action {

    val listPatients = coorServ.viewAllPatient
    val json = Json.toJson(listPatients)
    Ok(json)
  }

  def createCarePlan(care:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val carePlanModel = Json.fromJson[CarePlanModel](input).get
      val carePlanObj = carePlanModel.getDomain()
      val plan : Future[Long] = Future{coorServ.createCarePlan(carePlanObj)}

      plan.map(p =>
        Ok(Json.toJson(p))
      )
  }

  def addCaregiver(giver:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val caregiver = Json.fromJson[CaregiverModel](input).get
      val giverObj = caregiver.getDomain()
      val service = coorServ.addCaregiver(giverObj)
      val giver : Future[Long] = Future{service}

      giver.map(g =>
        Ok(Json.toJson(g))
      )
  }

  def addPatient(pat:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val patient = Json.fromJson[PatientModel](input).get
      val patObj = patient.getDomain()
      val service = coorServ.addPatient(patObj)
      val pat : Future[Long] = Future{service}

      pat.map(p =>
        Ok(Json.toJson(p))
      )
  }

  def updateCoordinator(coor:String) = Action.async(parse.json){

    request =>
      val input = request.body
      val coordinator = Json.fromJson[CoordinatorModel](input).get
      val coObj = coordinator.getDomain()
      val service = coorServ.updateCoordinator(coObj, coObj.coId)
      val co : Future[Long] = Future{service}

       co.map(c =>
        Ok(Json.toJson(c))
      )
  }

  def updateUser(user:String) = Action.async(parse.json){

    request =>
      val input = request.body
      val userD = Json.fromJson[UserModel](input).get
      val uObj = userD.getDomain()
      val service = coorServ.updateUser(uObj,uObj.userId)
      val us : Future[Long] = Future{service}

      us.map(u =>
        Ok(Json.toJson(u))
      )
  }

  def updateCaregiver(care:String) = Action.async(parse.json){

    request =>
      val input = request.body
      val caregiver = Json.fromJson[CaregiverModel](input).get
      val giverObj = caregiver.getDomain()
      val service = coorServ.updateCaregiver(giverObj,giverObj.caregiverId)
      val car : Future[Long] = Future{service}

      car.map(c =>
        Ok(Json.toJson(c))
      )
  }

  def updatePatient(pat:String) = Action.async(parse.json){

    request =>
      val input = request.body
      val patient = Json.fromJson[PatientModel](input).get
      val patObj = patient.getDomain()
      val service = coorServ.updatePatient(patObj,patObj.patientId)
      val pati : Future[Long] = Future{service}

      pati.map(p =>
        Ok(Json.toJson(p))
      )
  }

  def updateCarePlan(plan:String) = Action.async(parse.json){

    request =>
      val input = request.body
      val careplan = Json.fromJson[CarePlanModel](input).get
      val careplanObj = careplan.getDomain()
      val service = coorServ.updateCarePlan(careplanObj,careplanObj.planId)
      val care : Future[Long] = Future{service}

      care.map(c =>
        Ok(Json.toJson(careplanObj))
      )
  }

  def deleteCoordinator(id:Long) = Action {

    coorServ.deleteCoordinator(id)
    Ok("Deleted")
  }

  def deleteCaregiver(id:Long) = Action {

    coorServ.deleteCaregiver(id)
    Ok("Deleted")
  }

  def deletePatient(id:Long) = Action {

    coorServ.deletePatient(id)
    Ok("Deleted")
  }

  def deleteCarePlan(id:Long) = Action {

    coorServ.deleteCarePlan(id)
    Ok("Deleted")
  }
}
