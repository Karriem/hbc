package domainTest

import domain.Contact
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestContact extends FeatureSpec with GivenWhenThen {
  feature(" Save Contact") {
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val contact = Contact(Some("5415151518"), "0786119726", "tonata93@gmail.com",
        Some(0), Some(0), Some(900), Some(0), Some(4000), None)

      assert(contact.cellNumber == "0786119726")
    }
  }

}
