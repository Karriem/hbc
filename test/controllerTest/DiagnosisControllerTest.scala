package controllerTest

import java.util

import com.google.gson.Gson
import domain.{QuestionAnswer, Disease}
import model.{DiseaseModel, QuestionAnswerModel, DiagnosisModel}
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.specs2.runner.JUnitRunner
import play.api.libs.json._
import play.api.test.{WithApplication, FakeRequest}
import play.api.test.Helpers._
import org.specs2.mutable.Specification

/**
 * Created by tonata on 10/2/14.
 */
@RunWith(classOf[JUnitRunner])
class DiagnosisControllerTest extends Specification{

  "Controllers" should {
    "Should save diagnosis objects" in new WithApplication {
      val gson = new Gson()

      val diag = DiagnosisModel("1", "Asthmatic", "Antibiotics", "2014-07-07", 1L, "Workplace Routine")

      //val disease = DiseaseModel(1L, "Asthma", "Excessive Coughing", 1L)

      val qARec1 = QuestionAnswerModel("How long has the symptoms persisted?", "2 weeks", 1L)
      val qARec2 = QuestionAnswerModel("Have you had a check up", "No", 1L)
      val qARec3 = QuestionAnswerModel("How often has the coughing persisted?", "all day everyday", 1L)

      val qS = qARec1 + "#" + qARec2 + "#" + qARec3
      val jsonStr = gson.toJson(qS).stripMargin

      var list = new util.ArrayList[String]()
      list.add(jsonStr)

      val a = gson.toJson(list).stripMargin
      val diaStr = gson.toJson(diag).stripMargin
      //val b = Json.parse(a)

      val json: JsValue = JsObject(Seq
        (
            "list" -> JsString(a),
            "diagnosis" -> JsString(diaStr)
          )
      )
      val Some(result) = route(FakeRequest(
        POST, "/api/diagnosis/create/:dia").withJsonBody(json))

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
  }




}
