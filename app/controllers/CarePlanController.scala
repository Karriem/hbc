package controllers

import domain.CarePlan
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

  def listCarePlan(ids:Long) = Action {

    //val id = (ids).toLong

    val care = careServ.getCarePlan(ids)
    val json = Json.toJson(care)
    Ok(json)
  }

  def index = Action {

    Ok(views.html.index("Your new application is ready."))

  }
}

