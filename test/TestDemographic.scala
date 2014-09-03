import domain.Demographic
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestDemographic extends FeatureSpec with GivenWhenThen {
  feature(" Save Demographic") {
    val dob = new DateTime(1978 , 8, 12 , 0 ,0)
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val demo = Demographic(32, "Male", dob,
        Some(""), Some(""), Some(""), Some("C454"))

      assert(demo.caregiverId == Some("C454"))

    }
  }

}
