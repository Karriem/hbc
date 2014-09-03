import domain.QuestionAnswer
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by karriem on 9/3/14.
 */
class TestQuestionAnswer extends FeatureSpec with GivenWhenThen {
  feature(" Save Questions and Answers") {
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Respository")
      val qa = new QuestionAnswer("When did you start coughing", Some(""), "Dia40054")

      assert(qa.diagnosisId == "Dia40054")
    }
  }

}
