package model

import domain.Category
import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/17.
 */
case class CategoryModel (description:String,
                          level:String,
                          dailyReportId:Long,
                          hbcPackage:String,
                          score:Int){
  def getDomain() : Category = CategoryModel.domain(this) }

object CategoryModel{
  implicit lazy val categoryFmt = Json.format[CategoryModel]

  def domain(model: CategoryModel )={

    Category(model.description,
              model.level,
              model.dailyReportId,
              model.hbcPackage,
              model.score)
  }
}
