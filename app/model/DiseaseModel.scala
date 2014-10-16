package model

import domain.Disease
import play.api.libs.json.Json

/**
 * Created by tonata on 10/16/14.
 */
case class DiseaseModel( diseaseId:Long,
                         diseaseType:String,
                         symptoms:String,
                         diagnosisId:Long)
{ def getDomain() : Disease = DiseaseModel.domain(this)}

object DiseaseModel {
  implicit lazy val diseaseFmt = Json.format[DiseaseModel]

  def domain(model: DiseaseModel) = {
    Disease(model.diseaseId,
            model.diseaseType,
            model.symptoms,
            model.diagnosisId)
  }
}
