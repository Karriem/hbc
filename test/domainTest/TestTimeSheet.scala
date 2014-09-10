package domainTest

import domain.TimeSheet
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestTimeSheet extends FeatureSpec with GivenWhenThen {
  feature(" Save Time Sheet") {

    val wd = "8/2/2014"
    val ti = "15:00:00"
    val to = "17:00:00"

    info("As a Derived")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val tm = new TimeSheet(wd,ti,to,Some(1),Some(4),Some(8))

      assert(tm.visitId == 1L)
    }
  }

}
