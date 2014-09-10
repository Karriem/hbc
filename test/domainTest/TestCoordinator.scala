package domainTest

import domain.Coordinator
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestCoordinator extends FeatureSpec with GivenWhenThen {
  feature(" Save Coordinator") {
    info("As an Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val coO = Coordinator(7400, "LALA", "Hippo")

      assert(coO.coId == 7400)

    }
  }

}
