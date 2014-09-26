package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class CarePlan (
                        planId:Long,
                        description:String,
                        startDate:Date,
                        endDate:Date,
                        patientId:Long,
                        coordinator:Long
                      )

object CarePlans{
  implicit lazy val carePlanFmt = Json.format[CarePlan]
}
