import domain.Address
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestAddress extends FeatureSpec with GivenWhenThen {
  feature(" Save Address") {
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val add = Address("301 Alley Road", "301 Alley Road", "8080",
                          Some(""), Some(""), Some("P1001"), Some(""), Some(""))

      assert(add.patientId == Some("P1001"))
      assert(add.postalAddress == "301 Alley Road")
    }
  }
}