package services

import domain._
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.DailyReportModel.DailyReportRepo
import repository.PatientModel.PatientRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo
import repository.TimeSheetModel.TimeSheetRepo
import services.impl.{DailyReportServiceImpl, DiagnosisServiceImpl}

import scala.collection.mutable.ListBuffer
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/09/23.
 */
class DailyReportServiceTest extends FeatureSpec with GivenWhenThen{

  feature("Daily Report Service") {
    info("I want to carry out specific daily report services")

    scenario("Creating object instances"){
      Given("Specific entity information")

      val dailyReportRepo = TableQuery[DailyReportRepo]
      val timesheetRepo = TableQuery[TimeSheetRepo]
      //val cat = TableQuery[CategoryRepo]
      //val dia = TableQuery[DiagnosisRepo]
      val caregiverRepo = TableQuery[CaregiverRepo]
      val patientRepo = TableQuery[PatientRepo]

      val wd = new DateTime(2014 , 2, 8, 0, 0)
      val ti = new DateTime(2014 , 2, 8, 8, 30)
      val to = new DateTime(2014 , 2, 8, 12, 0)

      val flwUpDate = DateTime.parse("2014-07-07")

      val dailyReport = DailyReport(1l, "Administed Medication", None, 1L, 1L)

      val timeSheet = TimeSheet(wd.toDate, ti.toDate, to.toDate, None, None, None)

      val category = Category("Critical", "2", 1L, "Things", 3)

      val caregiver = Caregiver(1L, "Nathan", "Nakashololo")


      val patient = Patient(1L, DateTime.parse("2013-03-14").toDate, DateTime.parse("2014-03-14").toDate , "Leratho", "Kanime",  "Stuff", "24548844", "Stuff", "Japanese", "Things")


      val reportService : DailyReportService = new DailyReportServiceImpl()

      val diagnosis = Diagnosis(1, "Burn wounds", "Cream and Antibiotics", flwUpDate.toDate, None, "Workplace Routine")

      val qAndA = QuestionAnswer("When did it occur?", Option("3 days ago"), 1L)

      val disease = Disease(1L, "3rd Degree Burns", "Burn Wounds", 1L)

      val diaService : DiagnosisService = new DiagnosisServiceImpl()
      var qList = new ListBuffer[QuestionAnswerRepo#TableElementType]()
      qList += qAndA

      var id: Long = 0L
      var careID: Long = 0L
      var patID: Long = 0L

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        def testCreateReport = {
        // (cat.ddl).create
         //(dailyReportRepo.ddl).create
         //(dia.ddl).create
          val caregiverID = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiver)
          val patientID = patientRepo.returning(patientRepo.map(_.patientId)).insert(patient)

          val dID = diaService.createDiagnosis(diagnosis/*, disease*/, qList.toList)
          reportService.createDailyReport(dailyReport, timeSheet, category /*, caregiverID, patientID*/, dID)

          dailyReportRepo foreach { case (report: DailyReport) =>
            if(report.servicesRendered == "Administed Medication" ){
              id = report.dailyReportId
              careID = report.caregiverId
              patID = report.patientId
              assert(report.servicesRendered == "Administed Medication")
            }
          }
        }

        def testGetTimesheetDetials ={
          val sheet = reportService.getTimeSheetDetails(id)
          assert(sheet.timeIn == ti.toDate)
        }

        def testGetCategory = {
          val cat = reportService.getCategory(id)
          assert(cat.level == "2")
        }

        def testGetDiagnosis = {
          val diagnosises = reportService.getDiagnosis(id)
          diagnosises foreach {case (d: Diagnosis) =>
            val dType = d.diagnosisType
            assert(dType == "Burn wounds")
          }
        }

        def testGetReportPerPatient ={
          val patientsReports = reportService.getReportByPatient(patID)
          patientsReports foreach { case (r: DailyReport) =>
            val services = r.servicesRendered
            if (services == "Provided Medication") {
              assert(services == "Provided Medication")
            }
          }
        }

        def testGetReportPerCaregiver = {
          val caregiverReports = reportService.getReportByCaregiver(careID)
          caregiverReports foreach { case (r: DailyReport) =>
            val services = r.servicesRendered
            if (services == "Administed Medication") {
              assert(services == "Administed Medication")
            }
          }
        }

        def testGetAllReports = {
          val reports = reportService.getAllReports()
          reports foreach { case (r: DailyReport) =>
            val rID = r.dailyReportId
            timesheetRepo foreach { case (t: TimeSheet) =>
              if(t.dailyReportId == Option(rID)){
                assert(t.timeIn == ti.toDate)
              }
            }
          }

        }

        info("Creating Report")
        testCreateReport
        info("Retrieving TimeSheet")
        testGetTimesheetDetials
        info("Retrieving Category")
        testGetCategory
        info("Retrieving Diagnosis")
        testGetDiagnosis
        info("Retrieving report per patient")
        testGetReportPerPatient
        info("Retrieving report per caregiver")
        testGetReportPerCaregiver
        info("Retrieving all reports")
        testGetAllReports
      }

    }
  }


}

