package DomainTest

import domain.CarePlan
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestCarePlan extends FeatureSpec with GivenWhenThen {

  val startDate = new DateTime(2014, 9, 3, 0 , 0)
  val endDate = new DateTime(2014, 10, 3, 0 , 0)

  feature(" Save Care Plan") {
    info("As a Coordinator")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val plan = CarePlan("P1011", "Resupply", startDate , endDate)

      assert(plan.startDate == startDate)
      assert(plan.endDate == endDate)

    }
  }
}
