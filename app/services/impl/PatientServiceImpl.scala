package services.impl

import domain.{Adherence, CarePlan, Patient, Diagnosis}
import repository.AdherenceModel.AdherenceRepo
import repository.CarePlanModel.CarePlanRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import services.PatientService
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by phakama on 2014/09/23.
 */
class PatientServiceImpl extends  PatientService {

  val patientRepo = TableQuery[PatientRepo]
  val diagnosisRepo = TableQuery[DiagnosisRepo]
  val dailyReportRepo = TableQuery[DailyReportRepo]
  val adherenceRepo = TableQuery[AdherenceRepo]

 /* override def addPatient(patient: Patient, adherence : Adherence) : Long={

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val addPatient = patientRepo.returning(patientRepo.map(_.patientId)).insert(patient)
      val adh = Adherence(adherence.adType, adherence.instructions, addPatient)

      val newAdherence = adherenceRepo.insert(adh)

      //newAdherence
      addPatient
    }
  }*/


  override def getDiagnosis(id: Long): Diagnosis = {//List[DiagnosisRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val diag = diagnosisRepo.list
      val diagnosis = dailyReportRepo.list

      val diagList = diagnosis.filter(_.dailyReportId == id).map(_.dailyReportId).head
      val diagnosisList = diag.filter(_.dailyReportId == Option(diagList))

      println("Diagnosis: " + diagnosisList)
      diagnosisList.head

    }
  }

  /*override def createAdherence(adherence: Adherence): Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     // val newAdherence = adherenceRepo.returning(adherenceRepo.map(_.patientId)).insert(adherence)
      val newAdherence = adherenceRepo.insert(adherence)
      println("New Adherence" + adherence)
      newAdherence
    }
    }*/

  override def getAdherence(id: Long): Adherence = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val adh = adherenceRepo.list

      val patAdherence = adh.filter(_.patientId == id).head
      patAdherence
    }

  }
}
