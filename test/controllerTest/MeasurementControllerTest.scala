package controllerTest

import com.google.gson.Gson
import domain.Measurement
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by tonata on 10/8/14.
 */
@RunWith(classOf[JUnitRunner])
class MeasurementControllerTest extends Specification {

  "Controllers" should{
    "Should save measurement object" in new WithApplication{

      val gson = new Gson()
      val measurementRecord = Measurement(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25, 19, 19 )
      val measureJsonString = gson.toJson(measurementRecord).stripMargin
      val patID = gson.toJson(19).stripMargin
      val careID = gson.toJson(19).stripMargin
      val json = Json.parse(measureJsonString)
      val json1 = Json.parse(patID)
      val json2 = Json.parse(careID)

      val result = route(FakeRequest(
        POST, "/measurement/create/:m/:pID/:cID").withJsonBody(json).withJsonBody(json1).withJsonBody(json2)
      )

      result must beSome
      status(result.get) must equalTo(OK)
      contentType(result.get) must beSome.which(_ == "application/json")
      //println(result)
      //status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      //contentType(result) must beSome("application/json")
    }
  }





}
