import domain.ContactPerson
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestContactPerson extends FeatureSpec with GivenWhenThen {
  feature(" Save ContactPerson") {
    info("As an Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val contactP = ContactPerson("C3576")

      assert(contactP.personId == "C3576")
    }
  }

}
