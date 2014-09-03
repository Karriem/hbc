package DomainTest

import domain.DailyReport
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestDailyReport extends FeatureSpec with GivenWhenThen {
  feature(" Save Daily Report") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val dReport = DailyReport("DP344" , "Immunization" , "MR457")

      assert(dReport.servicesRendered == "Immunization")

    }
  }

}
