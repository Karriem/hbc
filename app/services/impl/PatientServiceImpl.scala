package services.impl

import domain.{CarePlan, Patient, Diagnosis}
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

  override def addPatient(patient: Patient) : Long={

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val addPatient = patientRepo.returning(patientRepo.map(_.patientId)).insert(patient)
      addPatient
    }
  }


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

  override def displayCarePlan(id: Long): CarePlan = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

    val careplan = carePlanRepo.list
    val patientCare = patientRepo.list

    val plan = careplan.filter(_.patientId ==id)
    //val listPlan = patientCare.filter(_.patientId == careplan.head)

    println("Care plan for a specific patient: " + plan.head)
      plan.head
  }
}

 /* override def getPatient(id: Long): List[PatientRepo#TableElementType] = {

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      val patient = patientRepo.list

      val patientList = patient.filter(_.patientId == id)

      println("Retrieving the patient by id" + patientList)
      patientList
    }

    }*/
}
