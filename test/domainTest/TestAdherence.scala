package domainTest

import domain.Adherence
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestAdherence extends FeatureSpec with GivenWhenThen {
  feature(" Save Adherence") {
    info("As a Coordinatior")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val adherence = Adherence("Antibiotics" , "Drink 3 times a day", 85)

      assert(adherence.instructions == "Drink 3 times a day")

    }
  }

}
