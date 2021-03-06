package services

import domain._
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import services.impl.{DailyReportServiceImpl, DiagnosisServiceImpl, WeeklyReportServiceImpl}

import scala.collection.mutable.ListBuffer
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/09/23.
 */
class WeeklyReportServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Weekly Report Service") {
    info("I want to carry out specific weekly report services")

    scenario("Creating object instances") {
      Given("Specific entity information")
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        val caregiverRepo = TableQuery[CaregiverRepo]
        val patientRepo = TableQuery[PatientRepo]
        val monthlyReport = TableQuery[MonthlyReportRepo]

        val wd = new DateTime(2014 , 2, 8, 0, 0)
        val ti = new DateTime(2014 , 2, 8, 8, 30)
        val to = new DateTime(2014 , 2, 8, 12, 0)

        val fDate = DateTime.parse("2014-07-07")

        val refDate = DateTime.parse("2013-02-20")
        val referral = Referral (1L, refDate.toDate, None, 350, 2, "Stuff", 250, 5)

        val mDate = new DateTime(2014, 12, 1, 0, 0)
        val monthlyRecord = MonthlyReport(4, mDate.toDate, 2, 6, 10, 4, 3)

        val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)

        val dailyReport = DailyReport(1L, "Cleaned Burn wounds", None, 1L, 1L)

        val timeSheet = TimeSheet(wd.toDate, ti.toDate, to.toDate, None, None, None)

        val category = Category("Critical", "2", 1L, "Things", 3)

        val caregiver = Caregiver(1L, "Nathan", "Nakashololo")

        val patient = Patient(1L, DateTime.parse("2013-03-14").toDate, DateTime.parse("2014-03-14").toDate , "Leratho", "Kanime", "Stuff", "24548844", "Stuff", "Japanese", "Things")

        val reportService : DailyReportService = new DailyReportServiceImpl()

        val diagnosis = Diagnosis(1, "Burn wounds", "Cream and Antibiotics", fDate.toDate, None, "Workplace Routine")

        val qAndA = QuestionAnswer("When did it occur?", Option("3 days ago"), 1L)

        val disease = Disease(1L, "3rd Degree Burns", "Burn Wounds", 1L)

        val weekly = WeeklyReport(1L, DateTime.parse("2014-11-7").toDate, DateTime.parse("2014-11-14").toDate, "No transfer", 3 , Some(mReportID))

        val wRepo = TableQuery[WeeklyReportRepo]
        val diag = TableQuery[DiagnosisRepo]

        val diaService : DiagnosisService = new DiagnosisServiceImpl()
        val dailyReportService: DailyReportService = new DailyReportServiceImpl()
        val weeklyReportService: WeeklyReportService = new WeeklyReportServiceImpl()
        var qList = new ListBuffer[QuestionAnswerRepo#TableElementType]()
        qList += qAndA

        var mID: Long  = 0L

        def testCreateWeeklyReport() = {

          val caregiverID = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiver)
          val patientID = patientRepo.returning(patientRepo.map(_.patientId)).insert(patient)

          val dID = diaService.createDiagnosis(diagnosis/*, disease*/, qList.toList)
          val dlyID = dailyReportService.createDailyReport(dailyReport, timeSheet, category, /*caregiverID, patientID,*/ dID)

          val reportIDs =  new ListBuffer[Long]()
          reportIDs += dlyID
          //(diag.ddl).create

          mID = weeklyReportService.createWeeklyReport(weekly, referral, reportIDs.toList)

          assert(wRepo.filter(_.weeklyReportId === mID).list.length == 1)


        }

        def testGetTotalVisits ={
          val visits = weeklyReportService.getTotalVisits(mID)
          assert(visits == 1)
        }

        def testCheckForReferral = {
          val retrievedRef = weeklyReportService.checkForReferral(mID)
          assert(retrievedRef.referralDate == refDate.toDate)
        }

        def testGetAllDailyReports ={
          val reports = weeklyReportService.getAllDailyReports(mID)

          reports foreach {case (d: DailyReport) =>
            val dType = d.servicesRendered
            assert(dType == "Cleaned Burn wounds")
          }
        }

        def testGetWeeklyReport = {
          // val report = weeklyReportService.getWeeklyReport(DateTime.parse("2014-11-7").toDate)
          //assert(report.weekEndDate == DateTime.parse("2014-11-14").toDate )
        }

        info("Creating Weekly Report")
        testCreateWeeklyReport
        info("Retrieving Visits")
        testGetTotalVisits
        info("Retrieving referral")
        testCheckForReferral
        info("Retrieving all reports")
        testGetAllDailyReports
        //info("Retrieving a report")
        //testGetWeeklyReport
      }


    }
  }



}
