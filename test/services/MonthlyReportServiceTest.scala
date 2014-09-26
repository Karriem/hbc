package services

import domain._
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.MonthlyReportModel.MonthlyReportRepo
import services.impl.{MonthlyReportServiceImpl, DiagnosisServiceImpl, DailyReportServiceImpl}

import scala.collection.mutable.ListBuffer
import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by tonata on 2014/09/23.
 */
class MonthlyReportServiceTest extends FeatureSpec with GivenWhenThen {

  feature("Monthly Report Service") {
    info("I want to carry out specific monthly report services")

    scenario("Creating object instances") {
      Given("Specific entity information")

      val wd = new DateTime(2014 , 2, 8, 0, 0)
      val ti = new DateTime(2014 , 2, 8, 8, 30)
      val to = new DateTime(2014 , 2, 8, 12, 0)
      val mDate = new DateTime(2014, 12, 3, 0 ,0)

      val fDate = DateTime.parse("2014-07-07")

      val refDate = DateTime.parse("2013-02-20")
      val referral = Referral (1L, refDate.toDate, None)

      val dailyReport = DailyReport(1L, "Cleaned Burn wounds", None, 1L, 1L)

      val timeSheet = TimeSheet(wd.toDate, ti.toDate, to.toDate, None, None, None)

      val category = Category("Critical", "2", 1L)

      val caregiver = Caregiver(1L, "Nathan", "Nakashololo")

      val patient = Patient(1L, "2013/03/14", "2014/03/14" , "Leratho", "Kanime")

      val reportService : DailyReportService = new DailyReportServiceImpl()

      val diagnosis = Diagnosis(1, "Burn wounds", "Cream and Antibiotics", fDate.toDate, null)

      val qAndA = QuestionAnswer("When did it occur?", Option("3 days ago"), 1L)

      val disease = Disease(1L, "3rd Degree Burns", "Burn Wounds", 1L)

      val monthly = MonthlyReport(1L,mDate.toDate, 0)

      val mRepo = TableQuery[MonthlyReportRepo]

      val diaService : DiagnosisService = new DiagnosisServiceImpl()
      val dailyReportService: DailyReportService = new DailyReportServiceImpl()
      val monthlyReportService: MonthlyReportService = new MonthlyReportServiceImpl()

      var mID: Long  = 0L

      def testCreateMonthlyReport() = {
        val dID = diaService.createDiagnosis(diagnosis, disease, qAndA)
        val dlyID = dailyReportService.createDailyReport(dailyReport, timeSheet, category, caregiver, patient, dID)

        Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
          val reportIDs =  new ListBuffer[Long]()
          reportIDs += dlyID
          //(mRepo.ddl).create

          mID = monthlyReportService.createMonthlyReport(monthly, referral, reportIDs.toList)

          assert(mRepo.filter(_.monthlyReportId === mID).list.length == 1)
        }

      }

      def testGetTotalVisits ={
        val visits = monthlyReportService.getTotalVisits(mID)
        assert(visits == 1)
      }

      def testCheckForReferral = {
        val retrievedRef = monthlyReportService.checkForReferral(mID)
        assert(retrievedRef.referralDate == refDate.toDate)
      }

      def testGetAllDailyReports ={
        val reports = monthlyReportService.getAllDailyReports(mID)

        reports foreach {case (d: DailyReport) =>
          val dType = d.servicesRendered
          assert(dType == "Cleaned Burn wounds")
        }
      }

      def testGetMonthlyReport = {

      }

      info("Creating Monthly Report")
      testCreateMonthlyReport
      info("Retrieving Visits")
      testGetTotalVisits
      info("Retrieving referral")
      testCheckForReferral
      info("Retrieving all reports")
      testGetAllDailyReports
      //testGetMonthlyReport
    }
  }



}
