import domain.Coordinator
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestCoordinator extends FeatureSpec with GivenWhenThen {
  feature(" Save Coordinator") {
    info("As an Institution")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val coO = Coordinator("CO124" , "CP2454")

      assert(coO.coId == "CO124")

    }
  }

}
