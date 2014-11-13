package domainTest

import domain.Patient
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestPatient extends FeatureSpec with GivenWhenThen {
  feature(" Save Patient") {

    val doc = DateTime.parse("2012-05-12")
    val dov = DateTime.parse("2012-05-13")

    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val pat = Patient(1, doc.toDate, dov.toDate, "Jaul", "Pam",
        "Tom", "0784559100" , "Christian", "English", "CPR")

      assert(pat.dateOfEvaluation == dov.toDate)

    }
  }

}
