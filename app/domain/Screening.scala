package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 11/12/14.
 */
case class Screening (
                        screeningId:Long,
                        screener:String,
                        illnessType:String
                       )


object Screenings {
  implicit lazy val screeningFmt = Json.format[Screening]
}