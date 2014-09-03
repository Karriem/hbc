import domain.Category
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestCategory extends FeatureSpec with GivenWhenThen {
  feature(" Save Category") {
    info("As a User")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val cat = Category("Cat 1" , "1" , "DR303")

      assert(cat.description == "Cat 1")

    }
  }

}
