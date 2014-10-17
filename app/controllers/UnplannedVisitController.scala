package controllers


import domain.{Contact, Address, UnplannedVisit}
import model.{ContactModel, AddressModel, UnplannedVisitModel}
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
      val visit = (input \ "visit").as[String]
      val address = (input \ "address").as[String]
      val contact = (input \ "contact").as[String]

      val jsonVisit = Json.parse(visit)
      val jsonAddress = Json.parse(address)
      val jsonContact = Json.parse(contact)

      val visits = Json.fromJson[UnplannedVisitModel](jsonVisit).get
      val visitObj = visits.getDomain()

      val addressObj = Json.fromJson[AddressModel](jsonAddress).get
      val addressDom = addressObj.getDomain()

      val contactObj = Json.fromJson[ContactModel](jsonContact).get
      val contactDom = contactObj.getDomain()

      val aObj = Address(addressDom.streetAddress, addressDom.postalAddress, addressDom.postalCode,
        addressDom.personId, addressDom.instituteId, addressDom.patientId,
        addressDom.caregiverId, addressDom.coordinatorId, addressDom.unplannedVisitID)

      val cObj = Contact(contactDom.homeTel, contactDom.cellNumber, contactDom.email,
        contactDom.personId, contactDom.instituteId, contactDom.coordinatorId, contactDom.patientId,
        contactDom.caregiverId, contactDom.unplannedVisitID)

      val vObj = UnplannedVisit(visitObj.unplannedVisitID, visitObj.visitDate, visitObj.patientName,
        visitObj.patientLastName, visitObj.caregiverID)

      val results : Future[Long] = Future{unplannedVisitServ.createUnplannedVisit(vObj, aObj, cObj)}

      results.map(res =>
        Ok(Json.toJson(res)))
  }

  def getUnplannedVisits = Action {

    val visits = unplannedVisitServ.listAllUnplannedVisits()
    val json = Json.toJson(visits)
    Ok(json)
  }

}
