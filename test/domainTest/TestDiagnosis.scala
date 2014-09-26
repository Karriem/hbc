package domainTest

import domain.Diagnosis
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestDiagnosis extends FeatureSpec with GivenWhenThen {
  feature(" Save Diagnosis") {
    val followUpDate = DateTime.parse("2014-05-04")
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val add = Diagnosis(78, "TB", "Medication", followUpDate.toDate, Option(7))

      assert(add.followUpDate == followUpDate.toDate)
    }
  }

}
