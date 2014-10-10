package controllers

import domain.Contact
import play.api.mvc._
import play.api.libs.json._
import services.ContactService
import services.impl.ContactServiceImpl

/**
 * Created by phakama on 2014/10/10.
 */
object ContactController extends  Controller
{
  val contactservice : ContactService = new ContactServiceImpl
  implicit val contact = Json.writes[Contact]

  def getCaregiverContact(id: Long) = Action{

    val json = Json.toJson(contactservice.getCaregiverContact(id))
    Ok(json)
  }

  def getContactPerson(id: Long) = Action{

    val json = Json.toJson(contactservice.getPersonContact(id))
    Ok(json)
  }

  def getCoordnatorContact(id: Long) = Action{

    val json = Json.toJson(contactservice.getCoordinatorContact(id))
    Ok(json)
  }

  def getPatientContact(id: Long) = Action{

    val json = Json.toJson(contactservice.getPatientContact(id))
    Ok(json)
  }

  def getInstituteContact(id : Long) = Action{

    val json = Json.toJson(contactservice.getInstituteContact(id))
    Ok(json)
  }

  def getAllContacts = Action{

    val json = Json.toJson(contactservice.getAllContacts())
    Ok(json)
  }

}
