package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Schedule (
                        scheduleId:Long,
                        patientId:Long,
                        caregiverId:Long
                      )

object Schedules{
  implicit lazy val scheduleFmt = Json.format[Schedule]
}


