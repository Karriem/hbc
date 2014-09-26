package domainTest

import domain.Referral
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestReferral extends FeatureSpec with GivenWhenThen {
  feature("Save Referral") {

    val rd = "2014-12-08"

    info("As a Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val ref = new Referral(4, rd, Some(0))

      assert(ref.referralDate == rd)

    }
  }

}
