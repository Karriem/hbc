package domainTest

import domain.TimeSheet
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestTimeSheet extends FeatureSpec with GivenWhenThen {
  feature(" Save Time Sheet") {


    val wd = new DateTime(2014 , 2, 8, 0, 0)
    val ti = new DateTime(2014 , 2, 8, 15, 0)
    val to = new DateTime(2014 , 2, 8, 17, 0)

    info("As a Derived")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val tm = new TimeSheet(wd.toDate,ti.toDate,to.toDate,Some(1),Some(4),Some(8))

      assert(tm.visitId == Option(1L))
    }
  }

}
