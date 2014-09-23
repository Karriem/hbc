package services.impl

import domain.{Patient, Diagnosis}
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
  val carePlanRepo = TableQuery[CarePlanRepo]
  val dailyReportRepo = TableQuery[DailyReportRepo]

  override def addPatient(patient: Patient) {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      patientRepo.insert(patient)

    }
  }

  override def getDiagnosis(id: Long): List[DiagnosisRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val diag = diagnosisRepo.list
      val diagnosis = dailyReportRepo.list

      val diagList = diagnosis.filter(_.dailyReportId == id).map(_.dailyReportId)
      val diagnosisList = diag.filter(_.dailyReportId == diagList.head)

      println("Diagnosis: " + diagnosisList)
      diagnosisList

    }
  }

  override def displayCarePlan(id: Long): List[CarePlanRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

    val careplan = carePlanRepo.list
    val patientCare = patientRepo.list

    val plan = careplan.filter(_.patientId == id)
    //val listPlan = patientCare.filter(_.patientId == careplan.head)

    println("Care plan for a specific patient: " + plan)
      plan
  }
}

  override def getCarePlans(id: Long)//:  List[CarePlanRepo#TableElementType] ={

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>



    }
}
