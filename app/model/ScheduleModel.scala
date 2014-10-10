package model

import domain.Schedule
import play.api.libs.json.Json

/**
 * Created by tonata on 10/10/14.
 */
case class ScheduleModel ( scheduleId:Long,
                           patientId: String,
                           caregiverId:String){
  def getDomain() : Schedule = ScheduleModel.domain(this)
}

object ScheduleModel  {
  implicit lazy val scheduleFmt = Json.format[ScheduleModel]

  def domain(model: ScheduleModel) = {
    Schedule(model.scheduleId,
            (model.patientId).toLong,
            (model.caregiverId).toLong)
  }

}
