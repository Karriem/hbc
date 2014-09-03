package DomainTest

import domain.Referral
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestReferral extends FeatureSpec with GivenWhenThen {
  feature("Save Referral") {

    val rd = new DateTime(2014, 8, 3, 0, 0)

    info("As a Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val ref = new Referral("R1001", rd, Some(""))

      assert(ref.referralDate == rd)

    }
  }

}
