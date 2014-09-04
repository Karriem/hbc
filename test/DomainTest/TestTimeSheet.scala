package DomainTest

import domain.TimeSheet
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestTimeSheet extends FeatureSpec with GivenWhenThen {
  feature(" Save Time Sheet") {

    val wd = new DateTime(2014, 8, 3, 0, 0)
    val ti = new DateTime(2014, 8, 3, 5, 30)
    val to = new DateTime(2014, 8, 3, 10, 0)

    info("As a Derived")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val tm = new TimeSheet(wd, ti, to, "T10001", Some(""), Some(""))

      assert(tm.visitId == "T10001")
    }
  }

}
