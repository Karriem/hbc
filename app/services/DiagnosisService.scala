package services

import domain.{Diagnosis, Disease, QuestionAnswer}
import repository.DiagnosisModel.DiagnosisRepo

/**
 * Created by tonata on 9/18/14.
 */
trait DiagnosisService {

  def createDiagnosis(diagnosis: Diagnosis,
                      disease: Disease,
                      qAndA: QuestionAnswer) :Long

  //def createQuestionAndAnswers()

  def getDisease(id: Long): Disease

  def getAllDiagnosisByCaregiver(id:Long) : List[DiagnosisRepo#TableElementType]

  //def getAllQuestionAndAnswers()

  //def getQuestionAndAnswers(id: Long)

  def getDiagnosis(id: Long) : Diagnosis

}
