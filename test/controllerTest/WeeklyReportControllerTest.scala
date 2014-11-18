package controllerTest

import model.{ReferralModel, WeeklyReportModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.{JsString, JsObject, JsValue}
import play.api.test.{FakeRequest, WithApplication}
import com.google.gson.Gson
import play.api.test.Helpers._
import java.util

/**
 * Created by tonata on 2014/10/17.
 */
@RunWith(classOf[JUnitRunner])
class WeeklyReportControllerTest extends Specification{

  "Controllers" should{
    "Should save report objects" in new WithApplication{
      val gson = new Gson()

      val weekly = WeeklyReportModel(1L, "2014-11-7", "2014-11-14", "No transfer", "3" , "")

      val referral = ReferralModel(1L, "2013-02-20", "80", 200, 2, "Things", 350, 50)

      val dList = "ids" + "#" + "2" + "#" + "4" + "#" + "6" + "#"

      val reportStr = gson.toJson(weekly).stripMargin
      val referralStr = gson.toJson(referral).stripMargin
      val list = gson.toJson(dList).stripMargin

      var theList = new util.ArrayList[String]()
      theList.add(dList)

      val jsonList = gson.toJson(theList).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "report" -> JsString(reportStr),
            "referral" -> JsString(referralStr),
            "list" -> JsString(jsonList)
          )
      )

      println(json)
      val Some(result) = route(FakeRequest(
        POST, "/api/weeklyReport/create/:r").withJsonBody(json))

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

  }

}
