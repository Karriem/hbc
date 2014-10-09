package controllers


import domain.UnplannedVisit
import play.api.libs.json.Json
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.UnplannedVisitService
import services.impl.UnplannedVisitServiceImpl

/**
 * Created by tonata on 10/8/14.
 */
object UnplannedVisitController extends Controller{
  val unplannedVisitServ: UnplannedVisitService = new UnplannedVisitServiceImpl()

  implicit val qAWrites = Json.writes[UnplannedVisit]

  /*def createUnplannedVisit() = Action{

  }*/

  def getUnplannedVisits = Action {

    val visits = unplannedVisitServ.listAllUnplannedVisits()
    val json = Json.toJson(visits)
    Ok(json)
  }

}
