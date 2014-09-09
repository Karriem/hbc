package DomainTest

import domain.QuestionAnswer
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * Created by karriem on 9/3/14.
 */
class TestQuestionAnswer extends FeatureSpec with GivenWhenThen {
  feature(" Save Questions and Answers") {
    info("As a Caregiver")
    info(" I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")
      val qa = new QuestionAnswer("When did you start coughing", Some(""), 8)

      assert(qa.diagnosisId == 8)
    }
  }

}
