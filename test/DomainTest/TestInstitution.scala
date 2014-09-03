package DomainTest

import domain.Institution
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestInstitution extends FeatureSpec with GivenWhenThen {
  feature(" Save Institution") {
    info("As a Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val institue = Institution("I343" , "Clinic" , "Grabouw Clinic" , Some(""), "R334")

      assert(institue.instituteName == "Grabouw Clinic")

    }
  }

}
