package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Disease (
                     diseaseId:String,
                     diseaseType:String,
                     symptoms:String,
                     diagnosisId:String
                     )

object Disease{
  implicit lazy val diseaseFmt = Json.format[Disease]
}

