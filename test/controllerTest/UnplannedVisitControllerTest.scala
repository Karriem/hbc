package controllerTest

import com.google.gson.Gson
import domain.{UnplannedVisit, Caregiver}
import model.UnplannedVisitModel
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.libs.json.Json
import play.api.test.{FakeRequest, WithApplication}

/**
 * Created by tonata on 10/10/14.
 */
@RunWith(classOf[JUnitRunner])
class UnplannedVisitControllerTest extends Specification {

  "Controllers" should {
    "Should save unplanned visit object" in new WithApplication {
      val gson = new Gson()

      //val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
      //val id = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

      val unplannedVisitRecord = UnplannedVisitModel(1L, "2014-03-12", "Max", "Payne", "1" )
      val jsonStr = gson.toJson(unplannedVisitRecord).stripMargin
      val json = Json.parse(jsonStr)

      val Some(result) = route(FakeRequest(
        POST, "/unplannedVisit/create/:visit").withJsonBody(json)
      )

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

  }


}
