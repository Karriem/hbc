import domain.Medication
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestMedication extends FeatureSpec with GivenWhenThen {
  feature(" Save Medication") {
    info("As a Coordinatior")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val meds = Medication("Antibiotics" , "Drink 3 times a day", "P2435")

      assert(meds.instructions == "Drink 3 times a day")

    }
  }

}
