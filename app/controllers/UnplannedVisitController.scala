package controllers


import domain.UnplannedVisit
import model.UnplannedVisitModel
import play.api.libs.json.Json
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.UnplannedVisitService
import services.impl.UnplannedVisitServiceImpl
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by tonata on 10/8/14.
 */
object UnplannedVisitController extends Controller{
  val unplannedVisitServ: UnplannedVisitService = new UnplannedVisitServiceImpl()

  implicit val qAWrites = Json.writes[UnplannedVisit]

  def createUnplannedVisit(visit: String) = Action.async(parse.json){
      request =>

      val input = request.body
      val visits = Json.fromJson[UnplannedVisitModel](input).get
      val visitObj = visits.getDomain()
      val results : Future[Long] = Future{unplannedVisitServ.createUnplannedVisit(visitObj)}

        results.map(res =>
          Ok(Json.toJson(res)))
  }

  def getUnplannedVisits = Action {

    val visits = unplannedVisitServ.listAllUnplannedVisits()
    val json = Json.toJson(visits)
    Ok(json)
  }

}
