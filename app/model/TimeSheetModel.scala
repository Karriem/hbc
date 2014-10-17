package model

import domain.TimeSheet
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.Json

/**
 * Created by tonata on 2014/10/17.
 */
case class TimeSheetModel( workDay:String,
                           timeIn:String,
                           timeOut:String,
                           visitId:String,
                           dailyReportId:String,
                           scheduleId:String){
  def getDomain() : TimeSheet = TimeSheetModel.domain(this) }


object TimeSheetModel{
  implicit lazy val timeSheetFmt = Json.format[TimeSheetModel]

  def domain(model: TimeSheetModel)={

    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    var value : Long = 0
    var value2 : Long = 0
    var value3 : Long = 0

    if (model.visitId == ""){
      value
    }
    else
    if(model.visitId != ""){
      value = model.visitId.toLong
    }

    if (model.scheduleId == ""){
      value2
    }
    else
    if(model.scheduleId != ""){
      value2 = model.scheduleId.toLong
    }

    if (model.dailyReportId== ""){
      value2
    }
    else
    if(model.dailyReportId != ""){
      value2 = model.dailyReportId.toLong
    }

    TimeSheet(formatter.parseDateTime(model.workDay).toDate,
      formatter.parseDateTime(model.timeIn).toDate,
      formatter.parseDateTime(model.timeOut).toDate,
              Some(value),
              Some(value3),
              Some(value2))
  }
}
