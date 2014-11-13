package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 11/12/14.
 */
case class ClientAssessment ( activity:String,
                              category:Int,
                              referralId:Long
                            )

object ClientAssessments {

  implicit lazy val clientFmt = Json.format[ClientAssessment]
}
