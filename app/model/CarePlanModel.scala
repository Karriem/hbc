package model

import java.util.Date

import domain.CarePlan
import play.api.libs.json.Json

/**
 * Created by karriem on 10/7/14.
 */
case class CarePlanModel (planId:Long,
                          description:String,
                          startDate:Date,
                          endDate:Date,
                          patientId:Long,
                          coordinator:Long){

  def getDomain(): CarePlan = CarePlanModel.domain(this)
}

object CarePlanModel {

  implicit val careplanFmt = Json.format[CarePlanModel]

  def domain(model : CarePlanModel) = {

    CarePlan(model.planId,
      model.description,
      model.startDate,
      model.endDate,
      model.patientId,
      model.coordinator)
  }
}