package DomainTest

import domain.Disease
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestDisease extends FeatureSpec with GivenWhenThen {
  feature(" Save Disease") {
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val disease = Disease(450, "TB", "Coughing", 45)

      assert(disease.diseaseType == "TB")

    }
  }

}
