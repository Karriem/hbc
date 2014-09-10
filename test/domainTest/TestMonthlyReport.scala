package domainTest

import domain.MonthlyReport
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestMonthlyReport extends FeatureSpec with GivenWhenThen {
  feature(" Save Monthly Report") {
    info("As a Coordinatior")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val monthlyReport = MonthlyReport(4 , 5)

      assert(monthlyReport.visits == 5)

    }
  }

}
