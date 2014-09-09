package DomainTest

import domain.User
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestUser extends FeatureSpec with GivenWhenThen {
  feature(" Save User") {
    info("As a Administrator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val usr = new User(121, "Phakama", "12345", Some(0), Some(0))

      assert(usr.password == "12345")
    }
  }

}
