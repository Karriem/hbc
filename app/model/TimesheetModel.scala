package model

import java.util.Date
import play.api.libs.json.Json
import domain.TimeSheet
import repository.TimeSheetModel

/**
 * Created by phakama on 2014/10/16.
 */
case class TimeSheetModel (
                            workDay:Date,
                            timeIn:Date,
                            timeOut:Date,
                            visitId:Option[Long],
                            dailyReportId:Option[Long],
                            scheduleId:Option[Long]
                            ){
  def getDomain(): TimeSheet = TimeSheetModel.domain(this)

}
object TimeSheetModel {

  implicit val timesheetFmt = Json.format[TimeSheetModel]

  def domain(model: TimeSheetModel) = {

    TimeSheet(model.workDay,
      model.timeIn,
      model.timeOut,
      model.visitId,
      model.dailyReportId,
      model.scheduleId)
  }
}