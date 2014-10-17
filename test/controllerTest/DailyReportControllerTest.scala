package controllerTest

import model.{CategoryModel, TimeSheetModel, DailyReportModel}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.{JsNumber, JsString, JsObject, JsValue}
import play.api.test.{FakeRequest, WithApplication}
import com.google.gson.Gson
import play.api.test.Helpers._

/**
 * Created by tonata on 2014/10/17.
 */
@RunWith(classOf[JUnitRunner])
class DailyReportControllerTest extends Specification {

  "Controllers" should{
    "Should save report object" in new WithApplication{
      val gson = new Gson()

      val wd = new DateTime(2014 , 2, 8, 0, 0)
      val ti = new DateTime(2014 , 2, 8, 8, 30)
      val to = new DateTime(2014 , 2, 8, 12, 0)

      val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
      val str1 = formatter.print(wd);
      val str2 = formatter.print(ti);
      val str3 = formatter.print(to);


      val dailyReport = DailyReportModel(1l, "Cleaned Burn wounds", "", "1", "1")

      val timeSheet = TimeSheetModel(str1, str2, str3 ,"", "", "")

      val category = CategoryModel("Critical", "2", 1L)

      val reportStr = gson.toJson(dailyReport).stripMargin
      val timeSheetStr = gson.toJson(timeSheet ).stripMargin
      val catStr = gson.toJson(category).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "report" -> JsString(reportStr),
            "timesheet" -> JsString(timeSheetStr),
            "category" -> JsString(catStr),
            "diagnosis" -> JsNumber(1)
          )
      )
      val Some(result) = route(FakeRequest(
        POST, "/api/dailyReport/create/:r").withJsonBody(json))

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }

}
