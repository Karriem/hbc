package services

import domain.{Diagnosis, Disease, QuestionAnswer}

/**
 * Created by tonata on 9/18/14.
 */
trait DiagnosisService {

  def createDiagnosis(diagnosis: Diagnosis,
                      disease: Disease,
                      qAndA: QuestionAnswer) : Long

  def getDisease(id: Long): Disease

  def getDiagnosis(id: Long) : Diagnosis

  def getAllDiagnosisByCaregiver(id: Long) : List[Diagnosis]

}
