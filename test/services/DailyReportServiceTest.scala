package services

import domain._
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CategoryModel.CategoryRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.TimeSheetModel.TimeSheetRepo
import services.impl.{DailyReportServiceImpl, DiagnosisServiceImpl}

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

      val dailyReport = DailyReport(1l, "Cleaned Burn wounds", None, 1L, 1L)

      val timeSheet = TimeSheet("", "08:30", "12:00", None, None, None)

      val category = Category("Critical", "2", 1L)

      val caregiver = Caregiver(1L, "Nathan", "Nakashololo")

      val patient = Patient(1L, "2013/03/14", "2014/03/14" , "Leratho", "Kanime")

      val reportService : DailyReportService = new DailyReportServiceImpl()

      val diagnosis = Diagnosis(1, "Burn wounds", "Cream and Antibiotics", "7/07/2014", null)

      val qAndA = QuestionAnswer("When did it occur?", Option("3 days ago"), 1L)

      val disease = Disease(1L, "3rd Degree Burns", "Burn Wounds", 1L)

      val diaService : DiagnosisService = new DiagnosisServiceImpl()

      val dailyReportRepo = TableQuery[DailyReportRepo]
      val timesheetRepo = TableQuery[TimeSheetRepo]
      val cat = TableQuery[CategoryRepo]
      val dia = TableQuery[DiagnosisRepo]


      var id: Long = 0L
      var careID: Long = 0L
      var patID: Long = 0L

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        def testCreateReport = {
        // (cat.ddl).create
         //(dailyReportRepo.ddl).create
         //(dia.ddl).create

          val dID = diaService.createDiagnosis(diagnosis, disease, qAndA)
          reportService.createDailyReport(dailyReport, timeSheet, category, caregiver, patient, dID)

          dailyReportRepo foreach { case (report: DailyReport) =>
            if(report.servicesRendered == "Cleaned Burn wounds" ){
              id = report.dailyReportId
              careID = report.caregiverId
              patID = report.patientId
              assert(report.servicesRendered == "Cleaned Burn wounds")
            }
          }
        }

        def testGetTimesheetDetials ={
          val sheet = reportService.getTimeSheetDetails(id)
          assert(sheet.timeIn == "08:30")
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
            assert(services == "Cleaned Burn wounds")
          }
        }

        def testGetReportPerCaregiver = {
          val caregiverReports = reportService.getReportByCaregiver(careID)
          caregiverReports foreach { case (r: DailyReport) =>
            val services = r.servicesRendered
            assert(services == "Cleaned Burn wounds")
          }
        }

        def testGetAllReports = {
          val reports = reportService.getAllReports()
          reports foreach { case (r: DailyReport) =>
            val rID = r.dailyReportId
            timesheetRepo foreach { case (t: TimeSheet) =>
              if(t.dailyReportId == Option(rID)){
                assert(t.timeIn == "08:30")
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

