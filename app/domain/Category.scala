package domain

import play.api.libs.json.Json

/**
 * Created by karriem on 8/28/14.
 */
case class Category (
                      description:String,
                      level:String,
                      dailyReportId:String
                      )

object Category{
  implicit lazy val categoryFmt = Json.format[Category]
}


