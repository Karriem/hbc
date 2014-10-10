package controllersTest

import com.google.gson.Gson
import model._
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

      val user = UserModel(1, "ME", "No", "", "135")
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

      val co = CoordinatorModel(1, "Karriem", "Uchiha")
      val jsonstring = gson.toJson(co).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/addco/:coordinator").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Save Caregiver Object" in new WithApplication {

      val model = CaregiverModel(1, "Naruto", "Uzamaki")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/addgiver/:giver").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Save Patient Object" in new WithApplication {

      val model = PatientModel(1, "2014-08-05", "2014-08-06", "Ichigo", "Kurosaki")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        POST, "/coordinator/addpat/:pat").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Update Coordinator Record" in new WithApplication {

      val model = CoordinatorModel(151, "Natsu", "Dragneel")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/coordinator/upco/:coor").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Update User Record" in new WithApplication {

      val model = UserModel(65, "BigB", "WolfAmongUs", "151", "")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/coordinator/upuser/:user").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Update Caregiver Record" in new WithApplication {

      val model = CaregiverModel(145, "Yagami", "Light")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/coordinator/upgiver/:care").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Update Patient Record" in new WithApplication {

      val model = PatientModel(145, "2014-10-10", "2014-10-12", "Gon", "Freecs")
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/coordinator/uppat/:pat").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Update Care Plan Record" in new WithApplication {

      val model = CarePlanModel(128, "Check up", "2014-08-22", "2014-09-22", 133, 65)
      val jsonstring = gson.toJson(model).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/coordinator/upplan/:plan").withBody(json)
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
