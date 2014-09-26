package domainTest

import domain.MonthlyReport
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestMonthlyReport extends FeatureSpec with GivenWhenThen {
  feature(" Save Monthly Report") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val mDate = new DateTime(2014, 12, 1, 0 ,0)
      val monthlyReport = MonthlyReport(4 ,mDate.toDate,  5)

      assert(monthlyReport.visits == 5)

    }
  }

}
