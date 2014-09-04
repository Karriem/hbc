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
      val care = Caregiver("C1001", "S1001", "DR1001")

      assert(care.caregiverId == "C1001")
      assert(care.dailyReportId == "DR1001")
    }
  }

}
