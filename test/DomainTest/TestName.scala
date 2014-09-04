package DomainTest

import domain.Name
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestName extends FeatureSpec with GivenWhenThen {
  feature(" Save Name") {
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val demo = Name("Tonata", "Nakashololo", Some(""),
        Some(""), Some(""), Some("C454"))

      assert(demo.caregiverId == Some("C454"))

    }
  }

}
