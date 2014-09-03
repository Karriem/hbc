package DomainTest

import domain.Diagnosis
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestDiagnosis extends FeatureSpec with GivenWhenThen {
  feature(" Save Diagnosis") {
    val followUpDate = new DateTime(2014 , 11 , 3 ,0 ,0)
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val add = Diagnosis("Dia1243" , "AIDS" , "ARVs" , followUpDate , "DP354" )

      assert(add.followUpDate == followUpDate)

    }
  }

}
