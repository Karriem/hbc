package domainTest

import domain.Role
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestRole extends FeatureSpec with GivenWhenThen {
  feature(" Save Role") {
    info("As a Administrator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val rol = new Role(4, "Caregiver", Some(0))

      assert(rol.description == "Caregiver")

    }
  }

}
