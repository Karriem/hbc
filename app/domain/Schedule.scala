package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Schedule (
                      scheduleId:String

                      )

object Schedule{
  implicit lazy val scheduleFmt = Json.format[Schedule]
}


