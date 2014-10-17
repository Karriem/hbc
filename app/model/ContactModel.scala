package model

import domain.Contact
import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/17.
 */
case class ContactModel( homeTel:String,
                         cellNumber:String,
                         email:String,
                         personId:String,
                         instituteId:String,
                         coordinatorId:String,
                         patientId:String,
                         caregiverId:String,
                         unplannedVisitID:String){
  def getDomain() : Contact = ContactModel.domain(this) }

object ContactModel{
  implicit lazy val contactFmt = Json.format[ContactModel]

  def domain(model: ContactModel)={

    var value : Long = 0
    var value1 : Long = 0
    var value2 : Long = 0
    var value3 : Long = 0
    var value4 : Long = 0
    var value5 : Long = 0

    if (model.personId == ""){
      value
    }
    else
    if(model.personId != ""){
      value = model.personId.toLong
    }

    if (model.instituteId == ""){
      value1
    }
    else
    if(model.instituteId != ""){
      value1 = model.instituteId.toLong
    }

    if (model.coordinatorId == ""){
      value2
    }
    else
    if(model.coordinatorId != ""){
      value2 = model.coordinatorId.toLong
    }

    if (model.caregiverId == ""){
      value3
    }
    else
    if(model.caregiverId != ""){
      value3 = model.caregiverId.toLong
    }

    if (model.patientId == ""){
      value4
    }
    else
    if(model.patientId != ""){
      value4 = model.patientId.toLong
    }

    if (model.unplannedVisitID == ""){
      value5
    }
    else
    if(model.unplannedVisitID != ""){
      value5 = model.unplannedVisitID.toLong
    }

    Contact(Option(model.homeTel),
            model.cellNumber,
            model.email,
      Some(value),
      Some(value1),
      Some(value2),
      Some(value4),
      Some(value3),
      Some(value5))
  }
}
