/*package controllerTest

import com.google.gson.Gson
import domain.{QuestionAnswer, Diagnosis}
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
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
      val qARec1 = QuestionAnswer("How long has the symptoms persisted?", Option("2 weeks"), 1L)
      val qARec2 = QuestionAnswer("Have you had a check up", Option("No"), 1L)
      val qARec3 = QuestionAnswer("How often has the coughing persisted?", Option("all day everyday"), 1L)

      val qAService : QuestionAnswerService = new QuestionAnswerServiceImpl()
      var qList = new ListBuffer[QuestionAnswerRepo#TableElementType]()

      qList += qARec1
      qList += qARec2
      qList += qARec3

      qList.toList

      /*val Some(result) = route(FakeRequest(
        POST, "//questionAnswer/create/:qA").withJsonBody(json)
      )

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")*/

    }

  }

}*/
