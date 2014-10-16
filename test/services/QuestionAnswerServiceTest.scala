package services

import domain.{Diagnosis, QuestionAnswer}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.impl.QuestionAnswerServiceImpl

import scala.collection.mutable.ListBuffer
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 9/30/14.
 */
class QuestionAnswerServiceTest extends FeatureSpec with GivenWhenThen {
  feature("Questions and Answer Service") {
    info("I want to carry out specific monthly report services")

    scenario("Creating object instances") {
      Given("Specific entity information")

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val diagnosis = Diagnosis(1L, "", "", new DateTime(2014, 12, 9, 0 ,0).toDate, None, "Workplace Routine")
        val qARec1 = QuestionAnswer("How long has the symptoms persisted?", Option("2 weeks"), 1L)
        val qARec2 = QuestionAnswer("Have you had a check up", Option("No"), 1L)
        val qARec3 = QuestionAnswer("How often has the coughing persisted?", Option("all day everyday"), 1L)

        val qAService : QuestionAnswerService = new QuestionAnswerServiceImpl()
        val qARepo = TableQuery[QuestionAnswerRepo]
        var qList = new ListBuffer[QuestionAnswerRepo#TableElementType]()

        qList += qARec1
        qList += qARec2
        qList += qARec3
        var id : Long = 0L

        def testCreateQuestionsAnswer = {
          id =  qAService.createQuestionAndAnswers(/*2L,*/ qList.toList)
          qARepo foreach { case (q: QuestionAnswer) =>
            if(q.question == "How long has the symptoms persisted?"){
              assert(q.answer == "2 weeks")
            }
          }
        }

        def testGetQuestionsAnswers ={
          val list= qAService.getQuestionAndAnswers(id)

          list foreach { case (q: QuestionAnswer) =>
            if(q.question == "How often has the coughing persisted?" ){
              assert(q.answer == "all day everyday")
            }
          }
        }
      }



    }
  }
}
