package domainTest

import domain.Visit
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestVisit extends FeatureSpec with GivenWhenThen {
  feature(" Save Visit") {

    val nv = DateTime.parse("2014-05-04")
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val vi = new Visit(45, nv.toDate, 8)

      assert(vi.nextVisit == nv.toDate)
    }
  }

}
