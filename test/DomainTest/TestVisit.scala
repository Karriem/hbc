package DomainTest

import domain.Visit
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestVisit extends FeatureSpec with GivenWhenThen {
  feature(" Save Visit") {

    val nv = new DateTime(2014, 8, 3, 17, 0)

    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val vi = new Visit("V1001", nv, "C10001")

      assert(vi.nextVisit == nv)
    }
  }

}
