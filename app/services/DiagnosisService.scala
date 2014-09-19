package services

import domain.{Disease, QuestionAnswer, Diagnosis}

/**
 * Created by tonata on 9/18/14.
 */
trait DiagnosisService {

  def createDiagnosis(diagnosis: Diagnosis)

  def createQuestionAndAnswers()

  def getDisease(id: Long): Disease

  def getAllQuestionAndAnswers()

  def getQuestionAndAnswers(id: Long)

  def getDiagnosis(id: Long) : Diagnosis

}
