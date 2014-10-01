package controllers

import repository.AddressModel.AddressRepo
import services.AddressService
import services.impl.AddressServiceImpl
import play.api.libs.json._
import play.api.mvc._

/**
 * Created by phakama on 2014/10/01.
 */
class AddressController  extends Controller{

  val addressRepo = TableQuery[AddressRepo]
  val addressservice : AddressService = new AddressServiceImpl

  def listAddresses = Action{
    val json = Json.toJson(addressservice.getAllAddresses())//.list
    Ok(json)
  }
}
