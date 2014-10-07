package controllersTest

import com.google.gson.Gson
import domain.{Disease, QuestionAnswer, Diagnosis}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import play.api.Logger
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by tonata on 10/2/14.
 */
class DiagnosisControllerTest extends FeatureSpec with GivenWhenThen{

  feature("Saving Diagnosis Controller") {
    val gson = new Gson()

    scenario("Creating Diagnosis") {
      Given(" ")
      /*val diagnosis = Diagnosis(1, "Asthmatic", "Antibiotics", DateTime.parse("2014-07-07").toDate, None, "Workplace Routine")

      val qAndA = QuestionAnswer("How long has the coughing persisted", None, 1L)

      val disease = Disease(1L, "Asthma", "Excessive Coughing", 1L)

      val diagnosisJsonString = gson.toJson(diagnosis).stripMargin
      val json = Json.parse(diagnosisJsonString)

      val qAndAJsonString = gson.toJson(qAndA).stripMargin
      val json2 = Json.parse(qAndAJsonString)

      val diseaseJsonString = gson.toJson(disease).stripMargin
      val json3 = Json.parse(diseaseJsonString)

      val Some(result) = route(FakeRequest(
        POST, "/api/diagnosis").withJsonBody(json).withJsonBody(json3).withJsonBody(json2)
      )*/

      //assert(status(result) == OK)
    }
  }

}
