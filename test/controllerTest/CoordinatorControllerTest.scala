package controllerTest

import java.util.Date

import com.google.gson.Gson
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by karriem on 10/7/14.
 */
@RunWith(classOf[JUnitRunner])
class CoordinatorControllerTest extends Specification {

  val gson = new Gson()

  val tim1 = new Date()
  val tim2 = new Date()

  "Controllers" should {

    /*"Should Save User Object" in new WithApplication {

      val careplan = UserModel(1, "Yes", "Yes", Some(111), None)
      val jsonstring = gson.toJson(careplan).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/createuser/:user").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }*/

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
