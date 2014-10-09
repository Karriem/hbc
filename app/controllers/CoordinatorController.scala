package controllers

import domain._
import model.{CoordinatorModel, CarePlanModel, UserModel}
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

  def getUser(id:Long) = Action {

    val user = coorServ.getUser(id)
    val json = Json.toJson(user)
    Ok(json)
  }

  def createUser(user:String) = Action.async(parse.json){
    request =>
      val input = request.body
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

  /*def addCaregiver(giver:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val caregiver = Json.fromJson[Caregiver](input).get
      val caregiverObj = caregiver.copy(giver.toLong)
      val care = coorServ.addCaregiver(caregiverObj)

      care.map(c =>
        Ok(Json.toJson(caregiverObj))
      )
  }*/

  /*def addPatient(pat:String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val patient = Json.fromJson[Patient](input).get
      val patientObj = patient.copy(pat.toLong)
      val pat = coorServ.addCaregiver(patientObj)

      pat.map(p =>
        Ok(Json.toJson(patientObj))
      )
  }*/

  /*def updateCoordinator(coor:String, id:Long) = Action.async(parse.json){

    request =>
      val input = request.body
      val coordinator = Json.fromJson[Coordinator](input).get
      val coObj = coordinator.copy(coor.toLong)
      val co = coorServ.updateCoordinator(coObj,id)

      co.map(c =>
        Ok(Json.toJson(coObj))
      )
  }*/

  /*def updateUser(user:String, id:Long) = Action.async(parse.json){

    request =>
      val input = request.body
      val userD = Json.fromJson[User](input).get
      val userObj = userD.copy(user.toLong)
      val us = coorServ.updateUser(userObj,id)

      us.map(u =>
        Ok(Json.toJson(userObj))
      )
  }*/

  /*def updateCaregiver(care:String, id:Long) = Action.async(parse.json){

    request =>
      val input = request.body
      val caregiver = Json.fromJson[Caregiver](input).get
      val giverObj = caregiver.copy(care.toLong)
      val car = coorServ.updateCaregiver(giverObj,id)

      car.map(c =>
        Ok(Json.toJson(giverObj))
      )
  }*/

  /*def updatePatient(pat:String, id:Long) = Action.async(parse.json){

    request =>
      val input = request.body
      val patient = Json.fromJson[Patient](input).get
      val patObj = patient.copy(pat.toLong)
      val pati = coorServ.updatePatient(patObj,id)

      pati.map(p =>
        Ok(Json.toJson(patObj))
      )
  }*/

  /*def updateCarePlan(plan:String, id:Long) = Action.async(parse.json){

    request =>
      val input = request.body
      val careplan = Json.fromJson[CarePlan](input).get
      val careplanObj = careplan.copy(plan.toLong)
      val care = coorServ.updateCarePlan(careplanObj,id)

      care.map(c =>
        Ok(Json.toJson(careplanObj))
      )
  }*/

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
