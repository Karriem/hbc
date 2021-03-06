package domainTest

import domain.Demographic
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestDemographic extends FeatureSpec with GivenWhenThen {
  feature(" Save Demographic") {
    val dob = DateTime.parse("1978-12-08")
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val demo = Demographic(27, "Male", dob.toDate, Some(4), Some(0), Some(0), Some(0))

      assert(demo.dateOfBirth.equals(dob.toDate))

    }
  }

}
