/*package controllerTest

import java.util

import com.google.gson.Gson
import domain.{QuestionAnswer, Diagnosis}
import model.QuestionAnswerModel
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.QuestionAnswerService
import services.impl.QuestionAnswerServiceImpl

import scala.collection.mutable.ListBuffer
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 10/10/14.
 */
@RunWith(classOf[JUnitRunner])
class QuestionAnswerControllerTest extends Specification {

  "Controllers" should {
    "Should save question answer objects" in new WithApplication {

      val gson = new Gson()

      //val diagnosis = Diagnosis(1L, "", "", new DateTime(2014, 12, 9, 0 ,0).toDate, None, "Workplace Routine")
      val qARec1 = QuestionAnswerModel("How long has the symptoms persisted?", "2 weeks", 1L)
      val qARec2 = QuestionAnswerModel("Have you had a check up", "No", 1L)
      val qARec3 = QuestionAnswerModel("How often has the coughing persisted?", "all day everyday", 1L)

      var qList = new ListBuffer[QuestionAnswerModel]()
      qList += qARec1
      qList += qARec2
      qList += qARec3

      val qS = qARec1 + "#" + qARec2 + "#" + qARec3
      //val jsonstring = gson.toJson(qARec1).stripMargin
      //val jsonstring1 = gson.toJson(qARec2).stripMargin
      //val jsonstring2 = gson.toJson(qARec3).stripMargin
      //val jsonString3 = gson.toJson(1L).stripMargin
      //val qS = jsonstring + "#" + jsonstring1 + "#" + jsonstring2

      val jsonStr = gson.toJson(qS).stripMargin
      var list = new util.ArrayList[String]()
      //list.add(jsonString3)
      list.add(jsonStr)
      //list.add(jsonstring)
      //list.add(jsonstring1)
      //list.add(jsonstring2)

      val a = gson.toJson(list).stripMargin
      val b = Json.parse(a)

      val Some(result) = route(FakeRequest(
        POST, "/api/questionAnswer/create/:qA").withBody(b)
      )



      //val js = Json.obj("id" -> "1L", "questions" -> qList.toArray.toString)
      //val obj = Seq("1L", qList.toArray.toString)

      /*val jsonStr = gson.toJson(js).stripMargin
      val json = Json.parse(jsonStr)

      val Some(result) = route(FakeRequest(
        POST, "/questionAnswer/create/:qA").withJsonBody(json)
      )*/

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")

    }

  }

}*/
