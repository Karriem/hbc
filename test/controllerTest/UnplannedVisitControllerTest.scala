package controllerTest

import com.google.gson.Gson
import domain.{UnplannedVisit, Caregiver}
import model.{ContactModel, AddressModel, UnplannedVisitModel}
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by tonata on 10/10/14.
 */
@RunWith(classOf[JUnitRunner])
class UnplannedVisitControllerTest extends Specification {

  "Controllers" should {
    "Should save unplanned visit object" in new WithApplication {
      val gson = new Gson()

      val addressRecord = AddressModel("30 Chester Road", "30 Chester Road", "7700" , "", "" , "", "", "" , "")
      val contactRecord = ContactModel("021798000" , "0786119726", "n@gmail.com", "", "", "", "", "", "")

      val unplannedVisitRecord = UnplannedVisitModel(1L, "2014-03-12", "Max", "Payne", "1" )

      val visitStr = gson.toJson(unplannedVisitRecord).stripMargin
      val contactStr = gson.toJson(contactRecord).stripMargin
      val addressStr = gson.toJson(addressRecord).stripMargin


      val json: JsValue = JsObject(Seq
        (
            "visit" -> JsString(visitStr),
            "address" -> JsString(addressStr),
            "contact" -> JsString(contactStr)
          )
      )
      val Some(result) = route(FakeRequest(
        POST, "/api/unplannedVisit/create/:visit").withJsonBody(json)
      )

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

  }


}
