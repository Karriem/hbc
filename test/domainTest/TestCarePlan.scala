package domainTest

import domain.CarePlan
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestCarePlan extends FeatureSpec with GivenWhenThen {

  val startDate  =  DateTime.parse("2014-12-20")
  val endDate =  DateTime.parse("2014-12-22")

  feature(" Save Care Plan") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val plan = CarePlan(2000, "Helping out", startDate.toDate, endDate.toDate, 1000, 5000, "Problem", 1)

      assert(plan.startDate == startDate.toDate)

    }
  }
}
