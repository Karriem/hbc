package domainTest

import domain.ContactPerson
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestContactPerson extends FeatureSpec with GivenWhenThen {
  feature(" Save ContactPerson") {
    info("As an Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val contactP = ContactPerson(7800, "Sandra", "Larson")

      assert(contactP.personId == 7800)
    }
  }

}
