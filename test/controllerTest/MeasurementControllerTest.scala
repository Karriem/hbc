package controllerTest

import com.google.gson.Gson
import domain.Measurement
import model.MeasurementModel
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

/**
 * Created by tonata on 10/8/14.
 */
@RunWith(classOf[JUnitRunner])
class MeasurementControllerTest extends Specification {

  "Controllers" should{
    "Should save measurement object" in new WithApplication{

      val gson = new Gson()

      val measurementRecord = MeasurementModel("1", "2014-02-12", "65", "12", "25", "19", "19" )
      val patID = "19"
      val careID = "19"

      val measureJsonString = gson.toJson(measurementRecord).stripMargin
      val pID = gson.toJson(patID).stripMargin
      val cID = gson.toJson(careID).stripMargin

      val json = Json.parse(measureJsonString)
      val json1 = Json.parse(pID)
      val json2 = Json.parse(cID)

      /*var qList = new ListBuffer[String]
      qList += measureJsonString
      qList += pID
      qList += cID*/

      //val a = gson.toJson(qList.toList)

      //val j = Json.parse(a)
      val Some(result) = route(FakeRequest(
        POST, "/measurement/save/:m").withJsonBody(json)//.withJsonBody(json1).withJsonBody(json2)
      )
      //result must beSome
      //status(result.get) must equalTo(OK)
      //contentType(result.get) must beSome.which(_ == "application/json")
      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }





}
