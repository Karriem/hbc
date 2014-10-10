package controllers

import domain.ContactPerson
import play.api.mvc._
import play.api.libs.json._
import services.ContactPersonService
import services.impl.ContactPersonServiceImpl

/**
 * Created by phakama on 2014/10/10.
 */
object ContactPersonController extends Controller{

  val contactPerson : ContactPersonService = new ContactPersonServiceImpl
  implicit val contact = Json.writes[ContactPerson]

  def getContactPerson(id: Long) = Action{

    val json = Json.toJson(contactPerson.getContact(id))
    Ok(json)
  }

  def getAllContacts = Action{

    val json = Json.toJson(contactPerson.getAllContacts())
    Ok(json)
  }
}
