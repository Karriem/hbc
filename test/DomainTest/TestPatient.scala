package DomainTest

import domain.Patient
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestPatient extends FeatureSpec with GivenWhenThen {
  feature(" Save Patient") {

    val doc = "4/05/12"
    val dov = "5/05/12"

    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val pat = Patient(1, doc, dov, "Jaul", "Pam")

      assert(pat.dateOfEvaluation == dov)

    }
  }

}
