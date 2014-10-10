package controllers

import domain.{Caregiver, Address}
import play.api.libs.json._
import play.api.mvc._
import services.AddressService
import services.impl.AddressServiceImpl

/**
 * Created by phakama on 2014/10/02.
 */
object AddressController extends Controller {

  val addressservice : AddressService = new AddressServiceImpl

  implicit val addresses = Json.writes[Address]
  implicit val caregiver = Json.writes[Caregiver]

  def listContactPersonAddress(id: Long) = Action  {

    val json = Json.toJson(addressservice.getContactPersonAddress(id))
    Ok(json)
  }

  def listCoordinatorAddress(id: Long) = Action {

    val coord = Json.toJson(addressservice.getCoordinatorAddress(id))
    Ok(coord)
  }

  def listCaregiverAddress(id: Long) = Action{

    val json = Json.toJson(addressservice.getCaregiverAddress(id))
    Ok(json)
  }

  def listPatientAddress(id: Long) = Action{

    val json = Json.toJson(addressservice.getPatientAddress(id))
    Ok(json)
  }

  def listAllAddresses = Action{

    val json = Json.toJson(addressservice.getAllAddresses())
    Ok(json)
  }

}
