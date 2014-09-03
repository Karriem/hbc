import domain.Disease
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestDisease extends FeatureSpec with GivenWhenThen {
  feature(" Save Disease") {
    info("As a Patient")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val disease = Disease("DE234", "TB" , "Coughing" , "Dia1233")

      assert(disease.diseaseType == "TB")

    }
  }

}
