package DomainTest

import domain.CarePlan
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestCarePlan extends FeatureSpec with GivenWhenThen {

  val startDate = "8/09/2014"
  val endDate = "9/09/2014"

  feature(" Save Care Plan") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val plan = CarePlan(2000, "Helping out", startDate, endDate, 1000, 5000)

      assert(plan.startDate == startDate)
      assert(plan.endDate == endDate)

    }
  }
}
