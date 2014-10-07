package crudTest

import domain.{WeeklyReport, Referral, MonthlyReport}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CategoryModel.CategoryRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.ReferralModel.ReferralRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 10/7/14.
 */
class WeeklyReportCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Weekly Report") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val referalRepo = TableQuery[ReferralRepo]
      val catRepo = TableQuery[CategoryRepo]
      val weeklyReport = TableQuery[WeeklyReportRepo]
      val monthlyReportRepo = TableQuery[MonthlyReportRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(weeklyReport.ddl).create
        //(referalRepo.ddl).create
        //(catRepo.ddl).create
        //(monthlyReportRepo.ddl).create

        info("Creating a Weekly Report")
        val refDate = DateTime.parse("2014-05-23")
        val wSDate = new DateTime(2014, 3, 12, 0, 0)
        val wEDate = new DateTime(2014, 3, 19, 0, 0)

        val mDate = new DateTime(2014, 12, 1, 0 ,0)
        val monthlyReport = MonthlyReport(4 ,mDate.toDate,  2, 6, 10, 4, 3)
        val mReportID = monthlyReportRepo.returning(monthlyReportRepo.map(_.monthlyReportId)).insert(monthlyReport)

        val weeklyRecord = WeeklyReport(1L, wSDate.toDate, wEDate.toDate, "No transfer", 3, Some(mReportID))
        val wReportID = weeklyReport.returning(weeklyReport.map(_.weeklyReportId)).insert(weeklyRecord)

        val refRecord = Referral(1, refDate.toDate, Some(wReportID))
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        def Read(visits: Int, id: Long) = {
          weeklyReport foreach { case (report: WeeklyReport) =>
            if (report.weeklyReportId == id) {
              assert(report.visits == visits)

              referalRepo foreach{case (ref: Referral) =>
                if(ref.weeklyReportId == Option(id)){
                  assert(ref.referralDate == refDate.toDate)
                }
              }
            }
          }
        }

        def Update(visits:Int, id:Long) = {
          weeklyReport.filter(_.weeklyReportId === id).map(_.visits).update(visits)
          Read(visits, id)
        }

        def searchDelete(id: Long) : Int = {
          weeklyReport foreach { case (cr: WeeklyReport) =>
            assertResult(false) {
              weeklyReport.filter(_.weeklyReportId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          referalRepo.filter(_.weeklyReportId=== id).delete
          weeklyReport.filter(_.weeklyReportId === id).delete
          searchDelete(id)
        }

        info("Reading Weekly Report")
        Read(3, wReportID)
        info("Updating Weekly Report")
        Update(17, wReportID)
        info("Deleting Weekly Report")
        Delete(wReportID)
      }
    }
  }

}
