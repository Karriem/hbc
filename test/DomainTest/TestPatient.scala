package DomainTest

import domain.Patient
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestPatient extends FeatureSpec with GivenWhenThen {
  feature(" Save Patient") {

    val doc = new DateTime(2014, 9, 3, 0, 0)
    val dov = new DateTime(2014, 9, 3, 0, 0)

    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val pat = Patient("P1001", doc, dov)

      assert(pat.dateOfEvaluation == dov)

    }
  }

}
