package controllerTest

import com.google.gson.Gson
import model.{TimeSheetModel, ScheduleModel}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by tonata on 10/10/14.
 */
@RunWith(classOf[JUnitRunner])
class ScheduleControllerTest  extends Specification {

  "Controllers" should {
    "Should save schedule objects" in new WithApplication {

      val gson = new Gson()

      val scheduleRecord = ScheduleModel(13, "53", "45")
      val patID = 133  // make sure records exists
      val careID = 141

      val wd = new DateTime(2014 , 2, 8, 0, 0)
      val ti = new DateTime(2014 , 2, 8, 8, 30)
      val to = new DateTime(2014 , 2, 8, 12, 0)

      val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
      val str1 = formatter.print(wd)
      val str2 = formatter.print(ti)
      val str3 = formatter.print(to)

      val timeSheet = TimeSheetModel(str1, str2, str3 ,"", "", "")

      val scheduleJsonString = gson.toJson(scheduleRecord).stripMargin
      val timesheetJsonString = gson.toJson(timeSheet).stripMargin

      //val json = Json.parse(scheduleJsonString)
      val json: JsValue = JsObject(Seq
        (
           "schedule" -> JsString(scheduleJsonString),
            "patid" -> JsNumber(patID),
            "careid" -> JsNumber(careID),
           "timesheet" -> JsString(timesheetJsonString)

          )
      )

      val Some(result) = route(FakeRequest(
        POST, "/api/schedule/create/:s").withJsonBody(json)
      )

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }

}
