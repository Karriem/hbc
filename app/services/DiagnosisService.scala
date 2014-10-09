package services

import domain.{Diagnosis, Disease, QuestionAnswer}
import repository.DiagnosisModel.DiagnosisRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo

import scala.concurrent.Future

/**
 * Created by tonata on 9/18/14.
 */
trait DiagnosisService {

  def createDiagnosis(diagnosis: Diagnosis,
                      disease: Disease,
                      questionAnswerList: List[QuestionAnswerRepo#TableElementType]): Long

  def getDisease(id: Long): Disease

  def getAllDiagnosisByCaregiver(id:Long) : List[DiagnosisRepo#TableElementType]

  def getDiagnosis(id: Long) : Diagnosis

}
