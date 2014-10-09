package controllersTest

import com.google.gson.Gson
import model.{CoordinatorModel, CarePlanModel, UserModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by karriem on 10/7/14.
 */
@RunWith(classOf[JUnitRunner])
class CoordinatorControllerTest extends Specification {

  val gson = new Gson()

  "Controllers" should {

    "Should Save User Object" in new WithApplication {

      val user = UserModel("1", "ME", "No", "", "135")
      val jsonstring = gson.toJson(user).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/createuser/:user").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Save Care Plan Object" in new WithApplication {

      val plan = CarePlanModel(1, "Yes We Can", "2014-05-05", "2014-05-05", 350, 250)
      val jsonstring = gson.toJson(plan).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/createplan/:plan").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Save Coordinator Object" in new WithApplication {

      val co = CoordinatorModel("1", "Karriem", "Uchiha")
      val jsonstring = gson.toJson(co).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/addco/:coordinator").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Delete a Coordinator Record" in new WithApplication {

      val Some(co) = route(FakeRequest(
        DELETE, "/coordinator/delco/230")
      )
      status(co) must equalTo(OK)
      Logger.debug(" The Result is " + co)
      contentType(co) must beSome("text/plain")
    }

    "Should Delete a Caregiver Record" in new WithApplication {

      val Some(result) = route(FakeRequest(
        DELETE, "/coordinator/delgiver/344")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("text/plain")
    }

    "Should Delete a Patient Record" in new WithApplication {

      val Some(pat) = route(FakeRequest(
        DELETE, "/coordinator/delpat/376")
      )
      status(pat) must equalTo(OK)
      Logger.debug(" The Result is " + pat)
      contentType(pat) must beSome("text/plain")
    }

    "Should Delete a CarePlan Record" in new WithApplication {

      val Some(result) = route(FakeRequest(
        DELETE, "/coordinator/delcare/235")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("text/plain")
    }

  }
}
