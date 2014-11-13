package services.impl

import domain._
import repository.CaregiverModel.CaregiverRepo
import repository.CategoryModel.CategoryRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import repository.TimeSheetModel.TimeSheetRepo
import services.DailyReportService

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 9/18/14.
 */
class DailyReportServiceImpl extends DailyReportService {

  val dailyReportRepo = TableQuery[DailyReportRepo]
  val caregiverRepo = TableQuery[CaregiverRepo]
  val patientRepo = TableQuery[PatientRepo]
  val timesheetRepo = TableQuery[TimeSheetRepo]
  val categoryRepo = TableQuery[CategoryRepo]
  val diagnosisRepo = TableQuery[DiagnosisRepo]

  override def createDailyReport(report: DailyReport, timesheet: TimeSheet, category: Category, /*caregiver: Long, patient: Long,*/ diagnosisID: Long) :Long = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //val caregiverID = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiver)
     // val patientID = patientRepo.returning(patientRepo.map(_.patientId)).insert(patient)

      //val newReport = DailyReport(report.caregiverId, report.servicesRendered, report.weeklyReportId, caregiver, patient)
      val diaID = dailyReportRepo.returning(dailyReportRepo.map(_.dailyReportId)).insert(report)


      val updatedTimesheet = TimeSheet(timesheet.workDay, timesheet.timeIn, timesheet.timeOut, None, Option(diaID), None)
      timesheetRepo.insert(updatedTimesheet)

      val updatedCat = Category(category.description, category.level, diaID, category.hbcPackage, category.score)
      categoryRepo.insert(updatedCat)

      diagnosisRepo.filter(_.diagnosisId === diagnosisID).map(_.dailyReportId).update(Option(diaID))

      return diaID

    }
  }

  override def getTimeSheetDetails(id: Long): TimeSheet = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
       val retrievedSheet = timesheetRepo.filter(_.dailyReportId === id).list.head
       return retrievedSheet
    }
  }

  override def getCategory(id: Long): Category ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      val retrievedCategory = categoryRepo.filter(_.dailyReportId === id).list.head
      return retrievedCategory
    }
  }


  override def getDiagnosis(id: Long):List[DiagnosisRepo#TableElementType] = {
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val dList = diagnosisRepo.filter(_.dailyReportId === id).list
        dList
      }
    }

  override def getReportByPatient(id: Long) : List[DailyReportRepo#TableElementType]= {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      val patientSReports = dailyReportRepo.filter(_.patientId === id).list
      return patientSReports
    }
  }

  override def getReportByCaregiver(id: Long): List[DailyReportRepo#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
      val caregiversReports = dailyReportRepo.filter(_.caregiverId === id).list
      return caregiversReports
    }
  }

  override def getAllReports():List[DailyReportRepo#TableElementType] ={
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        return dailyReportRepo.list

    }
  }

}
