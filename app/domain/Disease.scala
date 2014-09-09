package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Disease (
                     diseaseId:Long,
                     diseaseType:String,
                     symptoms:String,
                     diagnosisId:Long
                     )

object Diseases{
  implicit lazy val diseaseFmt = Json.format[Disease]
}

