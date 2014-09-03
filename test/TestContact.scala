import domain.Contact
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestContact extends FeatureSpec with GivenWhenThen {
  feature(" Save Contact") {
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val contact = Contact(Some("0214590345"), "0786119726", "tonata93@gmail.com",
        Some(""), Some(""), Some("P1001"), Some(""))

      assert(contact.patientId == Some("P1001"))
      assert(contact.cellNumber == "0786119726")
    }
  }

}
