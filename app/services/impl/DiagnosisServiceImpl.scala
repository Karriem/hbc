package services.impl

import domain.{Disease, DailyReport, Diagnosis, QuestionAnswer}
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.DiseaseModel.DiseaseRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo
import services.DiagnosisService

import scala.collection.mutable.ListBuffer

/**
 * Created by tonata on 9/18/14.
 */

import scala.slick.driver.MySQLDriver.simple._

class DiagnosisServiceImpl extends DiagnosisService {

  val diagnosisRepo = TableQuery[DiagnosisRepo]
  val diseaseRepo = TableQuery[DiseaseRepo]
  val dailyReportRepo = TableQuery[DailyReportRepo]
  val qARepo = TableQuery[QuestionAnswerRepo]

  override def createDiagnosis(diagnosis: Diagnosis,
                               disease: Disease,
                               qAndA: QuestionAnswer): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val diagID = diagnosisRepo.returning(diagnosisRepo.map(_.diagnosisId)).insert(diagnosis)
      val updatedDisease = Disease(disease.diseaseId, disease.diseaseType, disease.symptoms, diagID)

      val dID = diseaseRepo.returning(diseaseRepo.map(_.diseaseId)).insert(updatedDisease)
      val updatedqAndA = QuestionAnswer(qAndA.question, qAndA.answer, diagID)
      qARepo.insert(updatedqAndA)

      return diagID
    }

  }

  override def getDisease(id: Long): Disease = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val retrievedDisease = diseaseRepo.filter(_.diagnosisId === id).list.head
      return retrievedDisease
    }

  }

  override def getDiagnosis(id: Long): Diagnosis = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val retrievedDiagnosis = diagnosisRepo.filter(_.diagnosisId === id).list.head
      return retrievedDiagnosis
    }

  }

  override def getAllDiagnosisByCaregiver(id: Long): List[DiagnosisRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val caregiverReportIDs = dailyReportRepo.filter(_.caregiverId === id).map(_.dailyReportId).list
      //val aList : List[DiagnosisRepo#TableElementType] =  List()
      var diagnosises = new ListBuffer[DiagnosisRepo#TableElementType]()


      caregiverReportIDs foreach { case (id: Long) =>
        val obj = diagnosisRepo.filter(_.dailyReportId === id).list.head
        diagnosises += obj
      }

      val aList = diagnosises.toList
      return aList

    }
  }
}
