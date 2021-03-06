package services.impl

import domain.{QuestionAnswer, Diagnosis}
import repository.DiagnosisModel.DiagnosisRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.QuestionAnswerService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/30/14.
 */
class QuestionAnswerServiceImpl extends QuestionAnswerService {

  val qARepo = TableQuery[QuestionAnswerRepo]
  val diagnosisRepo = TableQuery[DiagnosisRepo]

  override def createQuestionAndAnswers(/*diagnosisID: Long,*/ questionAnswerList: List[QuestionAnswerRepo#TableElementType]): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      var diagID : Long = 0L
      //val diagID = diagnosisRepo.returning(diagnosisRepo.map(_.diagnosisId)).insert(diagnosis)
      questionAnswerList foreach { case (q: QuestionAnswer) =>
        //val updatedQuestion = QuestionAnswer(q.question, q.answer, diagnosisID)
        qARepo.insert(q)
       // diagID = qARepo.filter(_.diagnosisId == q.diagnosisId).map(_.diagnosisId).list.head
      }

     return diagID
    }

  }

  override def getQuestionAndAnswers(id: Long): List[QuestionAnswerRepo#TableElementType] ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
          return qARepo.filter(_.diagnosisId === id).list
    }

  }

}
