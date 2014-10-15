package model

import domain.DailyReport
import play.api.libs.json.Json

/**
 * Created by tonata on 10/10/14.
 */
case class DailyReportModel(dailyReportId:Long,
                            servicesRendered:String,
                            weeklyReportId:Option[String],
                            caregiverId:String,
                            patientId:String) /*{
  def getDomain() :DailyReport = DailyReportModel.domain(this) }*/

object DailyReportModel{
  implicit lazy val dailyReportFmt = Json.format[DailyReportModel]

  /* def domain(model:DailyReportModel )={
    var value : Long = 0l

    if (model.weeklyReportId == ""){
      value
    }
    DailyReport(model.dailyReportId,
                model.servicesRendered,
                Some(value),
                (model.caregiverId).toLong,
                (model.patientId).toLong)
  }*/
}
