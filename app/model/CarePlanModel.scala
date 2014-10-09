package model

import domain.CarePlan
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by karriem on 10/7/14.
 */
case class CarePlanModel (planId:Long,
                          description:String,
                          startDate:String,
                          endDate:String,
                          patientId:Long,
                          coordinator:Long){

  def getDomain(): CarePlan = CarePlanModel.domain(this)
}

object CarePlanModel {

  implicit val careplanFmt = Json.format[CarePlanModel]

  def domain(model : CarePlanModel) = {

    CarePlan(model.planId,
      model.description,
      DateTime.parse(model.startDate).toDate,
      DateTime.parse(model.endDate).toDate,
      model.patientId,
      model.coordinator)
  }
}