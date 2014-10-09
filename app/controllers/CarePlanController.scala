package controllers

import domain.{Visit, Coordinator, Patient, CarePlan}
import play.api.libs.json._
import play.api.mvc._
import services.CarePlanService
import services.impl.CarePlanServiceImpl

/**
 * Created by karriem on 9/30/14.
 */

object CarePlanController extends Controller {

  val careServ : CarePlanService = new CarePlanServiceImpl

  implicit val careWrites = Json.writes[CarePlan]
  implicit val carePatientWrites = Json.writes[Patient]
  implicit val careCoordinatorWrites = Json.writes[Coordinator]
  implicit val careVisitWrite = Json.writes[Visit]

  def getCarePlan(ids:Long) = Action {

    val care = careServ.getCarePlan(ids)
    val json = Json.toJson(care)
    Ok(json)
  }

  /*def createCarePlan(care:String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val carePlan = Json.fromJson[CarePlan](input).get
      val carePlanObj = carePlan.copy(care.toLong)
      val plan = careServ.createPlan(carePlanObj)

      plan.map(p =>
        Ok(Json.toJson(carePlanObj))
      )
  }*/

  /*def updateCarePlan(care:CarePlan, id:Long) = Action {

    val plan = careServ.updateCarePlan(care, id)
    val json = Json.toJson(plan)
    Ok(json)
  }*/

  def getPatient(id:Long) = Action {

    val pat = careServ.getPatient(id)
    val json = Json.toJson(pat)
    Ok(json)
  }

  def getWhoIssuedPlan(id:Long) = Action {

    val coor = careServ.getPlanIssued(id)
    val json = Json.toJson(coor)
    Ok(json)
  }

  def getVisit(id:Long) = Action {

    val visit = careServ.getVisit(id)
    val json = Json.toJson(visit)
    Ok(json)
  }

  def index = Action {

    Ok(views.html.index("Your new application is ready."))
  }
}

