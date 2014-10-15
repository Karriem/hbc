package controllerTest

import com.google.gson.Gson
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.WithApplication

/**
 * Created by tonata on 10/10/14.
 */
@RunWith(classOf[JUnitRunner])
class ScheduleControllerTest  extends Specification {

  "Controllers" should {
    "Should save schedule objects" in new WithApplication {

      val gson = new Gson()

      /*val Some(result) = route(FakeRequest(
        POST, "//questionAnswer/create/:qA").withJsonBody(json)
      )

      println(result)
      status(result) must equalTo(OK)
      //Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")*/
    }
  }

}
