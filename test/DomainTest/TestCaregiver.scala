package DomainTest

import domain.Caregiver
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestCaregiver  extends FeatureSpec with GivenWhenThen {
  feature(" Save Caregiver") {
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val care = Caregiver(10001, 10001, 10001, "Micheal", "Lou")

      assert(care.caregiverId == 10001)
    }
  }

}
