package domainTest

import domain.WeeklyReport
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by tonata on 10/7/14.
 */
class TestWeeklyReport extends FeatureSpec with GivenWhenThen {
  feature(" Save Weekly Report") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val wSDate = new DateTime(2014, 3, 12, 0, 0)
      val wEDate = new DateTime(2014, 3, 19, 0, 0)

      val weeklyRecord = WeeklyReport(1L, wSDate.toDate, wEDate.toDate, "No transfer", 3, Some(2L))

      assert(weeklyRecord.visits == 3)

    }
  }

}
