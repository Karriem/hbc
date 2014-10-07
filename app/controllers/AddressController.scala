package controllers

import domain.{Caregiver, Address}
import play.api.libs.json.{Writes, Json}
import play.api.mvc.Action
import play.mvc._
import services.AddressService
import services.impl.AddressServiceImpl
import spray.can.parsing.Result.Ok

/**
 * Created by phakama on 2014/10/02.
 */
object AddressController extends Controller {

  val addressservice : AddressService = new AddressServiceImpl

  implicit val addresses = Json.writes[Address]
  implicit val caregiver = Json.writes[Caregiver]

  /*def listAddress(id: Long) = Action  {

    val add = addressservice.getAddressById(id)
    val json = Json.toJson(add)
    Ok(json)

  }*/

}
