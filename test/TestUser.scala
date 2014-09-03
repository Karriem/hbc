import domain.User
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestUser extends FeatureSpec with GivenWhenThen {
  feature(" Save User") {
    info("As a Administrator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val usr = new User("U1002", "Phakama", "12345", Some(""), Some(""))

      assert(usr.password == "12345")
    }
  }

}
