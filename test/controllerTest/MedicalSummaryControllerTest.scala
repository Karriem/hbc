package controllerTest

import com.google.gson.Gson
import model.MedicalSummaryModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by tonata on 10/8/14.
 */
@RunWith(classOf[JUnitRunner])
class MedicalSummaryControllerTest extends Specification {

  "Controllers" should{
    "Should save measurement object" in new WithApplication{

      val gson = new Gson()

      val measurementRecord = MedicalSummaryModel("1", "2014-02-12", "65", "12", "25", "19", "19", "Stuff", "Things", false, "Things")
      val patID = 25
      val careID = 45

      val measureJsonString = gson.toJson(measurementRecord).stripMargin

      val json: JsValue = JsObject(Seq
        (
          "object" -> JsString(measureJsonString),
          "patid" -> JsNumber(patID),
          "careid" -> JsNumber(careID)
      )
      )
      val Some(result) = route(FakeRequest(
        POST, "/api/measurement/create/:m").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }





}
