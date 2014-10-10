package controllers

import domain.Demographic
import play.api.mvc._
import play.api.libs.json._
import services.DemographicService
import services.impl.DemographicServiceImpl

/**
 * Created by phakama on 2014/10/10.
 */
object DemographicController extends Controller{

  val demoservices : DemographicService = new DemographicServiceImpl
  implicit val demo = Json.writes[Demographic]

  def getCaregiverDemos(id: Long) = Action{

    val json = Json.toJson(demoservices.getCaregiverDemo(id))
    Ok(json)
  }

  def getContactPersonDemos(id: Long) = Action{

    val json = Json.toJson(demoservices.getPersonDemo(id))
    Ok(json)
  }

  def getCoordinatorDemos(id: Long) = Action{

    val json = Json.toJson(demoservices.getCoordinatorDemo(id))
    Ok(json)
  }

  def getPatientDemos(id: Long) = Action{

    val json = Json.toJson(demoservices.getPatientDemo(id))
    Ok(json)
  }

  def getAllDemos = Action{

    val json = Json.toJson(demoservices.getAllDemos())
    Ok(json)
  }
}
