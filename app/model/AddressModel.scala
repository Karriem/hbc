package model

import domain.Address
import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/17.
 */
case class AddressModel( streetAddress:String,
                         postalAddress:String,
                         postalCode:String,
                         personId:String,
                         instituteId:String,
                         patientId:String,
                         caregiverId:String,
                         coordinatorId:String,
                         unplannedVisitID:String){
  def getDomain() : Address = AddressModel.domain(this) }

object AddressModel{
  implicit lazy val addressFmt = Json.format[AddressModel]

  def domain(model: AddressModel )={

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

    Address(model.streetAddress,
            model.postalAddress,
            model.postalCode,
            Some(value),
            Some(value1),
            Some(value4),
            Some(value3),
            Some(value2),
            Some(value5))
  }
}
